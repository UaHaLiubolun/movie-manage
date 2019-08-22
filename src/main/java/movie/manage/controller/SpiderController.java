/*
 * @projectName movie-manage
 * @package movie.manage.controller
 * @className movie.manage.controller.SpiderController
 * @copyright Copyright 2019 Thuisoft, Inc. All rights reserved.
 */
package movie.manage.controller;

import movie.manage.Result;
import cn.stark.spider.common.bean.DouBanRequest;
import movie.manage.redis.RedisConfig;
import movie.manage.redis.RedisScheduler;
import movie.manage.redis.Scheduler;
import cn.stark.spider.common.Request;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * SpiderController
 * @description TODO
 * @author liubolun
 * @date 2019年08月22日 17:52
 * @version 3.0.0
 */
@RestController
@RequestMapping(value = "spider")
@CrossOrigin
public class SpiderController {

    private RedissonClient redissonClient = Redisson.create(RedisConfig.getConfig());

    private Scheduler<Request> requestScheduler = new RedisScheduler<>("request", redissonClient);

    @PostMapping("add")
    public Mono<Result> add(@RequestBody DouBanRequest douBanRequest) {
        List<Request> requests = douBanRequest.generateRequest();
        requests.forEach(requestScheduler::push);
        return Mono.just(Result.success("成功了的"));
    }
}
