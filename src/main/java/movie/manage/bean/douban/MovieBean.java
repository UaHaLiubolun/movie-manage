package movie.manage.bean.douban;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "movie_test")
public class MovieBean {

    @Id
    private String id;

    private List<String> casts;

    private String cover;

    private Date date;

    private List<String> directors;

    @Field("movie_id")
    private String movieId;

    private String rate;

    private String star;

    private String title;

    private String url;
}
