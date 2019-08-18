package movie.manage.dto.douban.subject;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import movie.manage.dto.douban.Rating;

@Data
public class Comment {

    private Rating rating;
    @SerializedName(value = "useful_count")
    private Integer usefulCount;

    @SerializedName(value = "subject_id")
    private String subjectId;

    private String content;

    @SerializedName(value = "create_at")
    private String createAt;

    private String id;

    private Author author;
}
