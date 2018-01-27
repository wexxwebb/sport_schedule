package common;

import java.util.Arrays;

public class Log {

    Object[] objects;

    public Log(Object... objects) {
        this.objects = objects;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object obj : objects) {
            sb.append(obj.toString());
            sb.append(" ");
        }
        return sb.toString();
    }
}
