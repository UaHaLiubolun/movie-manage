package movie.manage.dto.douban.subject;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import movie.manage.dto.douban.Image;
import movie.manage.dto.douban.Rating;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Subject {

    private Rating rating;

    @SerializedName(value = "reviews_cont")
    private Integer reviewsCount;

    @SerializedName(value = "wish_count")
    private Integer wishCount;

    @SerializedName(value = "original_title")
    private String originalTitle;

    @SerializedName(value = "collect_count")
    private Integer collectCount;

    private Image images;

    private String year;

    private String alt;

    @Id
    private String id;

    @SerializedName(value = "pubdate")
    private String pubDate;

    private String title;

    private List<String> languages;

    private List<String> tags;

    private List<String> durations;

    private List<String> genres;

    private List<Person> writers;

    private List<Trailer> trailers;

    private List<Trailer> bloopers;

    private List<Person> casts;

    private List<Photo> photos;

    private String summary;

    private String subtype;

    private List<Person> directors;

    @SerializedName(value = "popular_reviews")
    private List<Review> popularReviews;

    @SerializedName(value = "popular_comments")
    private List<Comment> popularComments;
}
