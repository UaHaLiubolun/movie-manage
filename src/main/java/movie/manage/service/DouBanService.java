/*
 * @projectName movie-manage
 * @package movie.manage.service
 * @className movie.manage.service.DouBanHttpService
 * @copyright Copyright 2019 Thuisoft, Inc. All rights reserved.
 */
package movie.manage.service;

import movie.manage.bean.douban.FileInfo;
import movie.manage.bean.douban.MovieBean;
import movie.manage.dto.FileInfoRepository;
import movie.manage.dto.MovieRepository;
import movie.manage.dto.SubjectRepository;
import movie.manage.dto.douban.subject.Subject;
import movie.manage.service.retrofit.DouBanProxyHttpService;
import movie.manage.support.retrofit.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * DouBanHttpService
 * @description TODO
 * @author liubolun
 * @date 2019年05月13日 14:16
 * @version 3.0.0
 */
@Service
public class DouBanService {

    @Resource(name = "DouBanProxyHttpService")
    private DouBanProxyHttpService douBanProxyHttpService;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private FileInfoRepository fileInfoRepository;

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    public Mono<Subject> get(String id, boolean isUpdate) {
        Subject subject;
        if (isUpdate) {
            subject = getSubjectFormWeb(id);
            return subjectRepository.save(subject);
        } else {
            return subjectRepository.findById(id).switchIfEmpty(Mono.defer(() -> {
                Subject s = getSubjectFormWeb(id);
                return subjectRepository.save(s);
            }));
        }
    }

    public void saveFileInfo(List<FileInfo> fileInfos) {
        fileInfos.forEach(fileInfo -> {
            MovieBean movieBean = movieRepository.findByTitleOrderByDate(fileInfo.getName())
                    .switchIfEmpty(mongoTemplate.find(Query.query(where("title").regex(fileInfo.getName())), MovieBean.class).publishNext())
                    .publishNext().block();
            if (movieBean != null) {
                fileInfo.setMovieBean(movieBean);
            }
            fileInfoRepository.save(fileInfo).onErrorResume(e -> fileInfoRepository.findByMd5(fileInfo.getMd5()).flatMap(originFileInfo -> {
                if (originFileInfo.getMovieBean() == null) {
                    fileInfo.setId(originFileInfo.getId());
                    return fileInfoRepository.save(fileInfo);
                } else {
                    return Mono.just(fileInfo);
                }
            })).subscribe();
        });
    }

    private Subject getSubjectFormWeb(String id) {
        HttpResult<Subject> httpResult = douBanProxyHttpService.getSubject(id, "0df993c66c0c636e29ecbb5344252a4a");
        if (httpResult.isSuccess()) {
            return httpResult.getResult();
        } else {
            return new Subject();
        }
    }

}
