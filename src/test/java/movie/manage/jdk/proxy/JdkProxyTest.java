/*
 * @projectName movie-manage
 * @package movie.manage.jdk.proxy
 * @className movie.manage.jdk.proxy.JdkProxyTest
 * @copyright Copyright 2019 Thuisoft, Inc. All rights reserved.
 */
package movie.manage.jdk.proxy;

/**
 * JdkProxyTest
 * @description TODO
 * @author liubolun
 * @date 2019年05月10日 11:40
 * @version 3.0.0
 */
public class JdkProxyTest {

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

    }
}
