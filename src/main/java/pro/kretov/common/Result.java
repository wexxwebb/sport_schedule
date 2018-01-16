package pro.kretov.common;

public class Result<T> {

    private T result;
    private boolean success;
    private String Message;

    public Result(T result, boolean success, String message) {
        this.result = result;
        this.success = success;
        Message = message;
    }

    public T getResult() {
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return Message;
    }
}
