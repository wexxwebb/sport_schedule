package db.entities.Impl;


import db.entities.Person;
import db.entities.State;
import db.entities.UserData;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlType(propOrder = {"id", "personId", "login", "password", "stateId", "dateReg"})
@XmlAccessorType(XmlAccessType.FIELD)
public class UserDataImpl implements UserData {

    private long id;
    @XmlTransient
    private Person person;
    private long personId;
    private String login;
    private String password;
    @XmlTransient
    private State state;
    private long stateId;
    private String dateReg;

    public UserDataImpl() {
    }

    public UserDataImpl(long id, int personId, String login, String password, int stateId, String dateReg) {
        this.id = id;
        this.personId = personId;
        this.login = login;
        this.password = password;
        this.stateId = stateId;
        this.dateReg = dateReg;
    }

    public UserDataImpl(long id, Person person, String login, String password, State state, String dateReg) {
        this.id = id;
        this.person = person;
        this.login = login;
        this.password = password;
        this.state = state;
        this.dateReg = dateReg;
    }

    public UserDataImpl(long id, Person person, long personId, String login, String password, long stateId, String dateReg) {
        this.id = id;
        this.person = person;
        this.personId = personId;
        this.login = login;
        this.password = password;
        this.stateId = stateId;
        this.dateReg = dateReg;
    }

    public UserDataImpl(long personId, String login, String password, long stateId) {
        this.personId = personId;
        this.login = login;
        this.password = password;
        this.stateId = stateId;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public Person getPerson() {
        return person;
    }

    @Override
    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public long getPersonId() {
        return personId;
    }

    @Override
    public void setPersonId(long personId) {
        this.personId = personId;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }

    @Override
    public long getStateId() {
        return stateId;
    }

    @Override
    public void setStateId(long stateId) {
        this.stateId = stateId;
    }

    @Override
    public String getDateReg() {
        return dateReg;
    }

    @Override
    public void setDateReg(String dateReg) {
        this.dateReg = dateReg;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", personId=" + personId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", stateId=" + stateId +
                ", dateReg=" + dateReg +
                '}';
    }
}
