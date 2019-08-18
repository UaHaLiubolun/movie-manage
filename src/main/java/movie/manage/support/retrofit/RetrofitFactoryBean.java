package movie.manage.support.retrofit;

import org.springframework.beans.factory.FactoryBean;
import retrofit2.Retrofit;

public class RetrofitFactoryBean implements FactoryBean {

    private Retrofit retrofit;

    private Class serviceClass;

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public void setServiceClass(Class serviceClass) {
        this.serviceClass = serviceClass;
    }

    @Override
    public Object getObject() throws Exception {
        return retrofit.create(this.serviceClass);
    }

    @Override
    public Class<?> getObjectType() {
        return serviceClass;
    }
}
