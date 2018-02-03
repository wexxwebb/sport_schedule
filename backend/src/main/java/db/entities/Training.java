package db.entities;

import java.io.Serializable;

public interface Training extends Serializable {
    UserData getUser();

    void setUser(UserData user);

    long getId();

    void setId(long id);

    long getUserId();

    void setUserId(long userId);

    String getCreateDate();

    void setCreateDate(String createDate);

    String getTrainingDate();

    void setTrainingDate(String trainingDate);

    @Override
    String toString();
}
