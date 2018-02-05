package db.entities.Impl;


import db.entities.inter.UserData;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.sql.Date;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.SEQUENCE;

@XmlRootElement
@XmlType(propOrder = {"id", "login", "password", "dateReg"})
@XmlAccessorType(XmlAccessType.FIELD)

@Entity(name = "UserData")
@Table(name = "user_data")
public class UserDataImpl implements UserData {

    private long id;
    @XmlTransient
    private PersonImpl person;
    private String login;
    private String password;
    @XmlTransient
    private Date dateReg;
    private boolean enabled;
    private String role;

    public UserDataImpl() {
    }

    public UserDataImpl(long id, String login, String password, Date dateReg) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.dateReg = dateReg;
    }

    public UserDataImpl(long id, PersonImpl person, String login, String password, Date dateReg) {
        this.id = id;
        this.person = person;
        this.login = login;
        this.password = password;
        this.dateReg = dateReg;
    }

    public UserDataImpl(PersonImpl person, String login, String password, Date dateReg) {
        this.person = person;
        this.login = login;
        this.password = password;
        this.dateReg = dateReg;
    }

    public UserDataImpl(String login, String password, Date dateReg) {
        this.login = login;
        this.password = password;
        this.dateReg = dateReg;
    }

    public UserDataImpl(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Id
    @SequenceGenerator(name = "hibernateSeq", sequenceName = "user_data_seq", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "hibernateSeq")
    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @OneToOne(optional = false, fetch = EAGER)
    @Override
    public PersonImpl getPerson() {
        return person;
    }

    @Override
    public void setPerson(PersonImpl person) {
        this.person = person;
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
    public Date getDateReg() {
        return dateReg;
    }

    @Override
    public void setDateReg(Date dateReg) {
        this.dateReg = dateReg;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", dateReg=" + dateReg +
                '}';
    }
}
