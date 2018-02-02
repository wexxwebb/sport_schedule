package db.pojo;

import java.io.Serializable;

public interface UserData extends Serializable {
    int getId();

    void setId(int id);

    Person getPerson();

    void setPerson(Person person);

    int getPersonId();

    void setPersonId(int personId);

    String getLogin();

    void setLogin(String login);

    String getPassword();

    void setPassword(String password);

    State getState();

    void setState(State state);

    int getStateId();

    void setStateId(int stateId);

    String getDateReg();

    void setDateReg(String dateReg);

    @Override
    String toString();
}
