package db.xml.marshaling;

import java.nio.file.Path;

public class MarshallingResult {

    private Path path;
    private Class aClass;
    private boolean success;
    private String message;

    public MarshallingResult(Path path, Class marshClass, boolean success, String message) {
        this.path = path;
        this.aClass = marshClass;
        this.success = success;
        this.message = message;
    }

    public Path getPath() {
        return path;
    }

    public Class getaClass() {
        return aClass;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
