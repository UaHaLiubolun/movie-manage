package movie.manage.dto.douban.subject;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import movie.manage.dto.douban.Rating;

@Data
public class Review {
    private Rating rating;

    @SerializedName(value = "subject_id")
    private String subjectId;

    private String summary;

    @SerializedName(value = "create_at")
    private String createAt;

    private String id;

    private String alt;

    private Author author;

}
