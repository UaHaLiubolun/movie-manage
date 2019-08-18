package movie.manage.dto;

import movie.manage.bean.douban.MovieBean;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface MovieRepository extends ReactiveCrudRepository<MovieBean, String> {

    Flux<MovieBean> findByTitleOrderByDate(String title);

    Flux<MovieBean> findAllByOrderByDateDesc();
}
