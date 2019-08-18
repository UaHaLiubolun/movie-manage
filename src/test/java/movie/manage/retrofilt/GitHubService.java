/*
 * @projectName movie-manage
 * @package movie.manage.retrofilt
 * @className movie.manage.retrofilt.GitHubService
 * @copyright Copyright 2019 Thuisoft, Inc. All rights reserved.
 */
package movie.manage.retrofilt;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

/**
 * GitHubService
 * @description TODO
 * @author liubolun
 * @date 2019年04月24日 15:20
 * @version 1.1.0
 */
public interface GitHubService {

    @GET("user/{user}/repos")
    Call<ResponseBody> listRepos(@Path("user") String user);
}
