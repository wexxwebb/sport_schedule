package db.entities;

import java.io.Serializable;

public interface Person extends Serializable {
    long getId();

    void setId(long id);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getBirthday();

    void setBirthday(String birthday);

    Sex getSex();

    void setSex(Sex sex);

    long getSexId();

    void setSexId(long sexId);

    @Override
    String toString();
}
