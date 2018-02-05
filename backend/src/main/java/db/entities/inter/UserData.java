package db.entities.inter;

import db.entities.Impl.PersonImpl;

import java.io.Serializable;
import java.sql.Date;

public interface UserData extends Serializable {

    long getId();

    void setId(long id);

    Person getPerson();

    void setPerson(PersonImpl person);

    String getLogin();

    void setLogin(String login);

    String getPassword();

    void setPassword(String password);

    Date getDateReg();

    void setDateReg(Date dateReg);

    boolean isEnabled();

    void setEnabled(boolean enabled);

    String getRole();

    void setRole(String role);

    @Override
    String toString();
}
