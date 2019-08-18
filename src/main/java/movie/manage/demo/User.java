package movie.manage.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document
public class User {

    @Id
    private String id;
    @Indexed(unique = true)
    private String username;
    private String password;
    private List<String> roles;
    private String introduction;
    private String avatar;
    private String name;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date birthday;
}
