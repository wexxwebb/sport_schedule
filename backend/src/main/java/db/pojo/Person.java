package db.pojo;

import java.io.Serializable;

public interface Person extends Serializable {
    int getId();

    void setId(int id);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getBirthday();

    void setBirthday(String birthday);

    Sex getSex();

    void setSex(Sex sex);

    int getSexId();

    void setSexId(int sexId);

    @Override
    String toString();
}
