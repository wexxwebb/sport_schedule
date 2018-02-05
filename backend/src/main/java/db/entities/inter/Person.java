package db.entities.inter;

import db.entities.Impl.SexImpl;
import db.entities.Impl.UserDataImpl;

import java.io.Serializable;
import java.sql.Date;

public interface Person extends Serializable {

    long getId();

    void setId(long id);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    Date getBirthday();

    void setBirthday(Date birthday);

    Sex getSex();

    void setSex(SexImpl sex);

    UserData getUserData();

    void setUserData(UserDataImpl userData);

    @Override
    String toString();
}
