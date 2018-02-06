package db.entities._inter;

import db.entities.Impl.PersonImpl;
import db.entities.Impl.TrainingImpl;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

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

    Collection<TrainingImpl> getTrainingCollection();

    void setTrainingCollection(Collection<TrainingImpl> trainingCollection);

    @Override
    String toString();
}
