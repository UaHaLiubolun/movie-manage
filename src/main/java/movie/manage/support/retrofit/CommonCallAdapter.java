package movie.manage.support.retrofit;

import movie.manage.dto.douban.Result;
import movie.manage.dto.douban.subject.Subject;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;

import java.io.IOException;
import java.lang.reflect.Type;

public class CommonCallAdapter implements CallAdapter {

    @Override
    public Type responseType() {
        return Subject.class;
    }

    @Override
    public Object adapt(Call call) {
        try {
            Response response = call.execute();
            if (response.isSuccessful()) {
                if (response.code() == 200) {
                    return HttpResult.success(response.body());
                } else {
                    return HttpResult.failed(response.code(), response.body());
                }
            } else {
                return HttpResult.failed(response.code(), response.body());
            }
        } catch (IOException e) {
            // todo 记录日志
            e.printStackTrace();
            return HttpResult.failed(null, e);
        }
    }
}
