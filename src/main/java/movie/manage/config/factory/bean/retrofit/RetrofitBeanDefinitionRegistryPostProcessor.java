package movie.manage.config.factory.bean.retrofit;

import movie.manage.annotations.RetrofitService;
import movie.manage.support.retrofit.CommonCallAdapterFactory;
import movie.manage.support.retrofit.RetrofitFactoryBean;
import org.reflections.Reflections;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Configuration
public class RetrofitBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    private Map<String, Retrofit> retrofitMap = new HashMap<>(1);

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        Reflections reflections = new Reflections("movie.manage.service.retrofit");
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(RetrofitService.class);
        for (Class<?> serviceClass : annotated) {
            for (Annotation annotation : serviceClass.getAnnotations()) {
                if (annotation instanceof RetrofitService) {
                    RootBeanDefinition beanDefinition = new RootBeanDefinition();
                    beanDefinition.setBeanClass(RetrofitFactoryBean.class);
                    beanDefinition.setLazyInit(true);
                    beanDefinition.getPropertyValues().addPropertyValue("retrofit", retrofit(annotation));
                    beanDefinition.getPropertyValues().addPropertyValue("serviceClass", serviceClass);
                    String beanName = serviceClass.getSimpleName();
                    registry.registerBeanDefinition(beanName, beanDefinition);
                }
            }
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    private Retrofit retrofit(Annotation annotation) {
        RetrofitService retrofitService = (RetrofitService) annotation;
        String baseApi = retrofitService.baseUrl();
        return retrofitMap.computeIfAbsent(baseApi,
                r -> new Retrofit.Builder().baseUrl(baseApi).addCallAdapterFactory(new CommonCallAdapterFactory())
                        .addConverterFactory(GsonConverterFactory.create()).build());
    }

}
