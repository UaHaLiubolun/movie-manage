/*
 * @projectName movie-manage
 * @package movie.manage.controller
 * @className movie.manage.controller.NewMovieController
 * @copyright Copyright 2019 Thuisoft, Inc. All rights reserved.
 */
package movie.manage.controller;

import movie.manage.Result;
import movie.manage.bean.douban.DouBanRequest;
import movie.manage.bean.douban.MovieBean;
import movie.manage.service.NewMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * NewMovieController
 * @description TODO
 * @author liubolun
 * @date 2019年08月22日 14:55
 * @version 3.0.0
 */
@RestController
@RequestMapping(value = "movie")
public class NewMovieController {

    @Autowired
    private NewMovieService movieService;

    @PostMapping("query")
    public Mono<Result> query(@RequestBody DouBanRequest douBanRequest) {
        Flux<MovieBean> movieBeanFlux = movieService.query(douBanRequest);
        return movieBeanFlux.collectList().map(Result::success);
    }
}
