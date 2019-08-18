/*
 * @projectName movie-manage
 * @package movie.manage.unsafe
 * @className movie.manage.unsafe.AtomicTest
 * @copyright Copyright 2019 Thuisoft, Inc. All rights reserved.
 */
package movie.manage.unsafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicTest
 * @description TODO
 * @author liubolun
 * @date 2019年05月09日 14:46
 * @version 3.0.0
 */
public class AtomicTest {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.getAndSet(10);
    }
}
