package movie.manage.service.retrofit;

import movie.manage.annotations.RetrofitService;
import movie.manage.dto.douban.Result;
import movie.manage.support.retrofit.HttpResult;
import retrofit2.http.GET;
import retrofit2.http.Query;

@RetrofitService(baseUrl = "https://api.douban.com")
public interface DouBanHttpService {

    @GET("/v2/movie/search")
    HttpResult<Result> searchMovie(@Query(value = "q") String q);
}
