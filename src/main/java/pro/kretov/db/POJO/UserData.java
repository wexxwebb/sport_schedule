package pro.kretov.db.POJO;


import java.sql.Date;

public class UserData {

    private int id;
    private Person person;
    private int personId;
    private String login;
    private String password;
    private State state;
    private int stateId;
    private java.sql.Date dateReg;

    public UserData(int id, int personId, String login, String password, int stateId, Date dateReg) {
        this.id = id;
        this.personId = personId;
        this.login = login;
        this.password = password;
        this.stateId = stateId;
        this.dateReg = dateReg;
    }

    public UserData(int id, Person person, String login, String password, State state, Date dateReg) {
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

    public Date getDateReg() {
        return dateReg;
    }

    public void setDateReg(Date dateReg) {
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
