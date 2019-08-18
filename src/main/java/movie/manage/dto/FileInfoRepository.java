package movie.manage.dto;

import movie.manage.bean.douban.FileInfo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FileInfoRepository extends ReactiveCrudRepository<FileInfo, String> {

    Mono<FileInfo> findByName(String name);

    Mono<FileInfo> findByMd5(String md5);

    Flux<FileInfo> findByRoot(String root);
}
