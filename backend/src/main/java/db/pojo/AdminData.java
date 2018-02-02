package db.pojo;

import java.io.Serializable;

public interface AdminData extends Serializable {
    UserData getUser();

    void setUser(UserData user);

    int getId();

    void setId(int id);

    int getUserId();

    void setUserId(int userId);
}
