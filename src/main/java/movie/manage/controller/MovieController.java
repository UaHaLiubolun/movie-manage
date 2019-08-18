package movie.manage.controller;

import movie.manage.Result;
import movie.manage.bean.douban.FileInfo;
import movie.manage.dto.FileInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@RestController
@CrossOrigin
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private FileInfoRepository fileInfoRepository;

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    @GetMapping
    public Mono getAll() {
        return fileInfoRepository.findAll().collect(Collectors.toList()).map(Result::success);
    }

    public Mono getMovie() {
        Flux<FileInfo> fileInfoFlux = fileInfoRepository.findAll().filter(fileInfo -> fileInfo.getMovieBean() != null);
        return fileInfoFlux.collectMultimap(FileInfo::getRoot, fileInfo -> fileInfo);
    }

    @GetMapping(value = "/search")
    public Mono<Result> search(@RequestParam(value = "q") String q) {
        return mongoTemplate
                .find(Query.query(where("name").regex(q)), FileInfo.class)
                .filter(fileInfo -> fileInfo.getMovieBean() != null)
                .collect(Collectors.toList()).map(Result::success);
    }

    @PostMapping
    public Mono<Result> save(@RequestBody FileInfo fileInfo) {
        return fileInfoRepository.save(fileInfo)
                .onErrorResume(
                        e -> fileInfoRepository.findByMd5(fileInfo.getMd5())
                                .flatMap(originFileInfo -> {
                                    fileInfo.setId(originFileInfo.getId());
                                    return fileInfoRepository.save(fileInfo);
                                })).map(Result::success);
    }

    @GetMapping(value = "/root")
    public Mono<Result> getMovieByRoot(@RequestParam(value = "root") String root) {
        return fileInfoRepository.findByRoot(root).filter(fileInfo -> fileInfo.getMovieBean() != null).map(fileInfo -> {
            fileInfo.getMovieBean().setCover(fileInfo.getMovieBean().getCover().replace("https", "http"));
            return fileInfo;
        }).collect(Collectors.toList()).map(Result::success);
    }


}
