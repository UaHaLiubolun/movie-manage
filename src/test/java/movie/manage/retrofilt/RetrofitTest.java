/*
 * @projectName movie-manage
 * @package movie.manage.retrofilt
 * @className movie.manage.retrofilt.RetrofitTest
 * @copyright Copyright 2019 Thuisoft, Inc. All rights reserved.
 */
package movie.manage.retrofilt;

import retrofit2.Retrofit;

/**
 * RetrofitTest
 * @description TODO
 * @author liubolun
 * @date 2019年04月24日 15:19
 * @version 1.1.0
 */
public class RetrofitTest {

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com/").build();

        GitHubService gitHubService = retrofit.create(GitHubService.class);

    }
}
