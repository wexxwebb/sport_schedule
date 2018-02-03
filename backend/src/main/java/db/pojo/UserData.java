package db.pojo;

import java.io.Serializable;

public interface UserData extends Serializable {
    long getId();

    void setId(long id);

    Person getPerson();

    void setPerson(Person person);

    long getPersonId();

    void setPersonId(long personId);

    String getLogin();

    void setLogin(String login);

    String getPassword();

    void setPassword(String password);

    State getState();

    void setState(State state);

    long getStateId();

    void setStateId(long stateId);

    String getDateReg();

    void setDateReg(String dateReg);

    @Override
    String toString();
}
