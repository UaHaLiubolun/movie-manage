package movie.manage.okhttp;

import okhttp3.*;

import java.io.IOException;

public class OkHttpTest {

    public static void main(String[] args) throws Exception {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("https://api.douban.com/v2/movie/in_theaters?city=成都&start=0&count=10").build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());
            }
        });

        while (true) {

        }
    }
}
