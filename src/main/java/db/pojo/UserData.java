package db.pojo;


import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlType(propOrder = {"id", "personId", "login", "password", "stateId", "dateReg"})
@XmlAccessorType(XmlAccessType.FIELD)
public class UserData {

    private int id;
    @XmlTransient
    private Person person;
    private int personId;
    private String login;
    private String password;
    @XmlTransient
    private State state;
    private int stateId;
    private String dateReg;

    public UserData() {
    }

    public UserData(int id, int personId, String login, String password, int stateId, String dateReg) {
        this.id = id;
        this.personId = personId;
        this.login = login;
        this.password = password;
        this.stateId = stateId;
        this.dateReg = dateReg;
    }

    public UserData(int id, Person person, String login, String password, State state, String dateReg) {
        this.id = id;
        this.person = person;
        this.login = login;
        this.password = password;
        this.state = state;
        this.dateReg = dateReg;
    }

    public UserData(int personId, String login, String password, int stateId) {
        this.personId = personId;
        this.login = login;
        this.password = password;
        this.stateId = stateId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public String getDateReg() {
        return dateReg;
    }

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
                ", dateReg=" + dateReg.toString() +
                '}';
    }
}
