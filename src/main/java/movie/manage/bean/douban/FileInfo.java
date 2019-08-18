package movie.manage.bean.douban;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fileInfo")
@Data
public class FileInfo {

    @Id
    private String id;

    private String root;

    private String name;

    private String address;

    private String suffix;

    private long size;
    @Indexed(unique = true)
    private String md5;

    private double time;

    private MovieBean movieBean;

}
