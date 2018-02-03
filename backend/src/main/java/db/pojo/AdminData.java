package db.pojo;

import java.io.Serializable;

public interface AdminData extends Serializable {
    UserData getUser();

    void setUser(UserData user);

    long getId();

    void setId(long id);

    long getUserId();

    void setUserId(long userId);
}
