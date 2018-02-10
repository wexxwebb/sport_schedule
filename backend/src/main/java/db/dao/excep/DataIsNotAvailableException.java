package db.dao.excep;

public class DataIsNotAvailableException extends Exception {

    private Exception exception;

    public DataIsNotAvailableException(Exception exception) {
        this.exception = exception;
    }

    public DataIsNotAvailableException(String message, Exception exception) {
        super(message);
        this.exception = exception;
    }

    public DataIsNotAvailableException(String message, Throwable cause, Exception exception) {
        super(message, cause);
        this.exception = exception;
    }
}
