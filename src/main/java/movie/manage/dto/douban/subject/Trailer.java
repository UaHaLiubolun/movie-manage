package movie.manage.dto.douban.subject;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Trailer {

    private String medium;

    private String title;

    @SerializedName(value = "subject_id")
    private String subjectId;

    private String alt;

    private String small;

    @SerializedName(value = "resource_url")
    private String resourceUrl;

    private String id;
}
