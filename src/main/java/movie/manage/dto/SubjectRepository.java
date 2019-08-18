package movie.manage.dto;

import movie.manage.dto.douban.subject.Subject;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface SubjectRepository extends ReactiveCrudRepository<Subject, String> {

}
