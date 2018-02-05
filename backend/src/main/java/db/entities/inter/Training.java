package db.entities.inter;

import db.entities.Impl.UserDataImpl;

import java.io.Serializable;
import java.sql.Date;

public interface Training extends Serializable {

    UserDataImpl getUser();

    void setUser(UserDataImpl userData);

    long getId();

    void setId(long id);

    Date getCreateDate();

    void setCreateDate(Date createDate);

    Date getTrainingDate();

    void setTrainingDate(Date trainingDate);

    @Override
    String toString();
}
