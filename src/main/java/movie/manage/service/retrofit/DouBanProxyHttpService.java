package movie.manage.service.retrofit;


import movie.manage.annotations.RetrofitService;
import movie.manage.dto.douban.subject.Subject;
import movie.manage.support.retrofit.HttpResult;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

@RetrofitService(baseUrl = "https://api.douban.com")
public interface DouBanProxyHttpService {

    @GET("/v2/movie/subject/{subject}")
    HttpResult<Subject> getSubject(@Path(value = "subject") String id, @Query(value = "apikey") String apiKey);
}
