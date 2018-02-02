package db.pojo;

import java.io.Serializable;

public interface State extends Serializable {
    boolean isEnabled();

    void setEnabled(boolean enabled);

    String getRole();

    void setRole(String role);

    int getId();

    void setId(int id);

    String getState();

    void setState(String state);

    @Override
    String toString();
}
