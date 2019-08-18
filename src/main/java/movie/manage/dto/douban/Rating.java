package movie.manage.dto.douban;

import lombok.Data;

@Data
public class Rating {

    private double max;
    private double average;
    private String stars;
    private double min;
    private RatingDetail details;
}
