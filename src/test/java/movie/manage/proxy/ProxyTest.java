package movie.manage.proxy;

import java.lang.reflect.Proxy;

public class ProxyTest {

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        Car car = (Car) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(), new Class[]{Car.class}, new CarProxy());
        car.getCar();
    }
}
