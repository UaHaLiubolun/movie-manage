package movie.manage.controller;


import movie.manage.bean.douban.FileInfo;
import movie.manage.bean.douban.MovieBean;
import movie.manage.dto.MovieRepository;
import movie.manage.dto.douban.Result;
import movie.manage.service.DouBanService;
import movie.manage.service.retrofit.DouBanHttpService;
import movie.manage.support.retrofit.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@CrossOrigin
@RestController
public class DoubanController {


    @Resource(name = "DouBanHttpService")
    private DouBanHttpService douBanHttpService;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private DouBanService douBanService;

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    @GetMapping(value = "/douban/search")
    public Mono<Result> searchByName(@RequestParam(value = "q") String q) {
        HttpResult<Result> call = douBanHttpService.searchMovie(q);
        if (call.isSuccess()) {
            return Mono.just(call.getResult());
        } else {
            return Mono.just(null);
        }
    }

    @GetMapping(value = "/getAllMovie")
    public Mono<movie.manage.Result> getAll() {
        return movieRepository.findAll().collectList().map(movie.manage.Result::success);
    }


    @GetMapping(value = "/douban/title")
    public Mono<movie.manage.Result> getByTitle(@RequestParam(value = "title") String title) {
        return movieRepository.findByTitleOrderByDate(title)
                .switchIfEmpty(mongoTemplate.find(Query.query(where("title").regex(title)), MovieBean.class))
                .distinct(MovieBean::getTitle)
                .collectList()
                .map(movie.manage.Result::success);
    }

    @GetMapping(value = "/douban/subject/{subjectId}")
    public Mono getSubject(@PathVariable(value = "subjectId") String subjectId,
                                    @RequestParam(value = "isUpdate", required = false) boolean isUpdate) {
        return douBanService.get(subjectId, isUpdate).map(movie.manage.Result::success);
    }

    @GetMapping(value = "/douban/latest")
    public Mono<movie.manage.Result> getLatest(@RequestParam(value = "limit", required = false, defaultValue = "100") int limit) {
        return movieRepository.findAllByOrderByDateDesc()
                .distinct(MovieBean::getTitle).take(limit).collectList().map(movie.manage.Result::success);
    }

    @PostMapping(value = "/updateMovie")
    public Mono<movie.manage.Result> updateMovie(@RequestBody List<MovieBean> movieBeans) {
        movieBeans.forEach(
                movieBean -> {
                    try {
                        movieRepository.save(movieBean).subscribe();
                    } catch (Exception e) {

                    }
                }
        );
        return Mono.just(movie.manage.Result.success(""));
    }

    @PostMapping(value = "/save")
    public Mono<String> save(@RequestBody List<FileInfo> fileInfos) {
        douBanService.saveFileInfo(fileInfos);
        return Mono.just("OK");
    }
}
