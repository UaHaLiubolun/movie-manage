/*
 * @projectName movie-manage
 * @package movie.manage.service
 * @className movie.manage.service.NewMovieService
 * @copyright Copyright 2019 Thuisoft, Inc. All rights reserved.
 */
package movie.manage.service;

import cn.stark.spider.common.bean.DouBanRequest;
import movie.manage.bean.douban.MovieBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * NewMovieService
 * @description TODO
 * @author liubolun
 * @date 2019年08月22日 14:56
 * @version 3.0.0
 */
@Service
public class NewMovieService {

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    public Flux<MovieBean> query(DouBanRequest douBanRequest) {
        Query query = generateQuery(douBanRequest);
        if (query == null) {
            return mongoTemplate.findAll(MovieBean.class);
        }
        return mongoTemplate.find(generateQuery(douBanRequest), MovieBean.class);
    }

    private Query generateQuery(DouBanRequest douBanRequest) {
        List<Criteria> criteriaList = new LinkedList<>();
        if (StringUtils.isNotBlank(douBanRequest.getCountries())) {
            criteriaList.add(where("countries").regex(douBanRequest.getCountries()));
        }
        if (StringUtils.isNotBlank(douBanRequest.getYear_range())) {
            criteriaList.add(where("year_range").regex(douBanRequest.getYear_range()));
        }
        if (StringUtils.isNotBlank(douBanRequest.getGenres())) {
            criteriaList.add(where("genres").regex(douBanRequest.getGenres()));
        }
        if (StringUtils.isNotBlank(douBanRequest.getTags())) {
            List<String> tagList = new ArrayList<>(Arrays.asList(douBanRequest.getTags().split(",")));
            List<Criteria> tagCriteriaList = new ArrayList<>(tagList.size());
            for (String s : tagList) {
                tagCriteriaList.add(where("tags").regex(s));
            }
            criteriaList.add(new Criteria().orOperator(tagCriteriaList.toArray(new Criteria[0])));
        }
        if (criteriaList.isEmpty()) {
            return null;
        }
        return Query.query(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));
    }

}
