/*
 * @projectName movie-manage
 * @package movie.manage.jdk.proxy
 * @className movie.manage.jdk.proxy.StudentHanlder
 * @copyright Copyright 2019 Thuisoft, Inc. All rights reserved.
 */
package movie.manage.jdk.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * StudentHanlder
 * @description TODO
 * @author liubolun
 * @date 2019年05月10日 11:48
 * @version 3.0.0
 */
public class StudentHanlder implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object result = null;
        try{
//            result = method.invoke(realSubject,args);
        }catch (Exception e){
            System.out.println("ex:"+e.getMessage());
            throw e;
        }finally {
            System.out.println("after");
        }
        return result;
    }
}
