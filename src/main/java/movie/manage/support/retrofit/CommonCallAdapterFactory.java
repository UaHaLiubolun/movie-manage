package movie.manage.support.retrofit;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class CommonCallAdapterFactory extends CallAdapter.Factory {

    // todo 类型不是活动
    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        if (getRawType(returnType) == HttpResult.class) {
            return new CommonCallAdapter();
        }
        return null;
    }
}
