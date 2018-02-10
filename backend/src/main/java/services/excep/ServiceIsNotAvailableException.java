package services.excep;

public class ServiceIsNotAvailableException extends Exception {

    private Exception exception;

    public ServiceIsNotAvailableException(Exception exception) {
        this.exception = exception;
    }

    public ServiceIsNotAvailableException(Throwable cause, Exception exception) {
        super(cause);
        this.exception = exception;
    }
}
