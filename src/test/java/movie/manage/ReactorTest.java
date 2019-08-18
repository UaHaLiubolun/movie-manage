package movie.manage;

import org.reactivestreams.Subscription;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;

public class ReactorTest {

    public static void main(String[] args) {
        Flux.just(1, 2, 3, 4, 5).map(i -> i * i).subscribe(new CoreSubscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                System.out.println("onSubscribe, " + s.getClass());
                s.request(5);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext: " + integer);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }
}
