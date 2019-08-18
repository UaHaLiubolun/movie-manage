package movie.manage.support.retrofit;

public class HttpResult<T> {

    public static<T> HttpResult success(T result, int code) {
        return new HttpResult<>(result, code, true);
    }

    public static<T> HttpResult success(T result) {
        return new HttpResult<>(result, 200, true);
    }

    public static<T> HttpResult failed(T result, Exception exception) {
        return new HttpResult<>(result, 0, exception, false);
    }

    public static<T> HttpResult failed(int code, T result, Exception exception) {
        return new HttpResult<>(result, code, exception, false);
    }

    public static<T> HttpResult failed(int code, T result) {
        return new HttpResult<>(result, code, null, false);
    }

    public HttpResult(T result, int code, boolean isSuccess) {
        this.result = result;
        this.code = code;
        this.isSuccess = isSuccess;
    }

    public HttpResult(T result, int code, Exception exception, boolean isSuccess) {
        this.result = result;
        this.code = code;
        this.exception = exception;
        this.isSuccess = isSuccess;
    }

    private T result;

    private int code;

    private boolean isSuccess;

    private Exception exception;


    public T getResult() {
        return result;
    }

    public int getCode() {
        return code;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public Exception getException() {
        return exception;
    }
}
