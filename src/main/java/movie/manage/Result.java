package movie.manage;

import lombok.Data;

@Data
public class Result {

    private int code;

    private Object data;

    private Result(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public static Result success(Object data) {
        return new Result(20000, data);
    }
}
