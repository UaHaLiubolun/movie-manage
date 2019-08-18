package movie.manage.dto.douban;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class RatingDetail {

    @SerializedName(value = "1")
    private Integer one;

    @SerializedName(value = "2")
    private Integer two;

    @SerializedName(value = "3")
    private Integer three;

    @SerializedName(value = "4")
    private Integer four;

    @SerializedName(value = "5")
    private Integer five;
}
