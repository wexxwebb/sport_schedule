package db.entities.Impl;


import db.entities._inter.Person;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

import java.sql.Date;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.SEQUENCE;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "firstName", "lastName", "birthday"})

@Entity(name = "Person")
@Table(name = "person")
public class PersonImpl implements Person {

    private long id;
    private String firstName;
    private String lastName;
    private Date birthday;
    private UserDataImpl userData;
    @XmlTransient
    private SexImpl sex;

    public PersonImpl() {
    }

    public PersonImpl(long id, String firstName, String lastName, Date birthday) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public PersonImpl(long id, String firstName, String lastName, Date birthday, SexImpl sex) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.sex = sex;
    }

    public PersonImpl(String firstName, String lastName, Date birthday, SexImpl sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.sex = sex;
    }

    public PersonImpl(String firstName, String lastName, Date birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public PersonImpl(String firstName, String lastName, Date birthday, UserDataImpl userData, SexImpl sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.userData = userData;
        this.sex = sex;
    }

    @Id
    @SequenceGenerator(name = "hibernateSeq", sequenceName = "person_data_seq", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "hibernateSeq")
    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public Date getBirthday() {
        return birthday;
    }

    @Override
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @ManyToOne(optional = true, cascade = ALL)
    @Override
    public SexImpl getSex() {
        return sex;
    }

    @Override
    public void setSex(SexImpl sex) {
        this.sex = sex;
    }

    @OneToOne(optional = false, mappedBy = "person")
    @Override
    public UserDataImpl getUserData() {
        return userData;
    }

    @Override
    public void setUserData(UserDataImpl userData) {
        this.userData = userData;
    }

    @Override
    public String toString() {
        return "person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            if (obj == this) return true;
            if (obj instanceof PersonImpl) {
                if (this.id == ((Person) obj).getId())
                    if (this.getFirstName().equals(((Person) obj).getFirstName()))
                        if (this.getLastName().equals(((Person) obj).getLastName()))
                            return this.getBirthday().equals(((Person) obj).getBirthday());
            }
        }
        return false;
    }
}
