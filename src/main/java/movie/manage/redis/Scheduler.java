package movie.manage.redis;


public interface Scheduler<E> {

    void push(E request);


    E poll();
}
