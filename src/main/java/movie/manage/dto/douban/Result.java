package movie.manage.dto.douban;

import lombok.Data;

import java.util.List;

@Data
public class Result {


    private int count;
    private int start;
    private int total;

    private List<Movie> subjects;
}
