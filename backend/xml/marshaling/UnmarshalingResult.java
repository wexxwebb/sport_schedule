package db.xml.marshaling;

public class UnmarshalingResult {

    private Object object;
    private Class umarshalClass;
    private boolean success;
    private String message;

    public UnmarshalingResult(Object object, Class umarshalClass, boolean success, String message) {
        this.object = object;
        this.umarshalClass = umarshalClass;
        this.success = success;
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public Class getUmarshalClass() {
        return umarshalClass;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
