package movie.manage.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CarProxy implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        System.out.println("after");
        return null;
    }
}
