package movie.manage.dto.douban;

import lombok.Data;

import java.util.List;

@Data
public class Movie {

    private Rating rating;
    private List<String> genres;
    private String title;
    private List<Cast> casts;
    private long collect_count;
    private String original_title;
    private String subtype;
    private List<Director> directors;
    private String year;
    private Image images;
    private String alt;
    private String id;
}
