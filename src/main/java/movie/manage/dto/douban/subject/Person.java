package movie.manage.dto.douban.subject;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import movie.manage.dto.douban.Image;

@Data
public class Person {

    private Image avatars;

    @SerializedName(value = "name_en")
    private String nameEn;

    private String name;

    private String alt;

    private String id;
}
