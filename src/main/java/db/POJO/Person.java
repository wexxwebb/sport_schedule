package db.POJO;


import common.SDF;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "firstName", "lastName", "sexId", "birthday"})

public class Person {

    private int id;
    private String firstName;
    private String lastName;
    private String birthday;
    @XmlTransient
    private Sex sex;
    private int sexId;

    public Person() {
    }

    public Person(int id, String firstName, String lastName, String birthday, int sexId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.sexId = sexId;
    }

    public Person(int id, String firstName, String lastName, String birthday, Sex sex) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.sex = sex;
        this.sexId = sex.getId();
    }

    public Person(String firstName, String lastName, String birthday, int sexId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.sexId = sexId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public int getSexId() {
        return sexId;
    }

    public void setSexId(int sexId) {
        this.sexId = sexId;
    }

    @Override
    public String toString() {
        return "person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", sexId=" + sexId +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            if (obj == this) return true;
            if (obj instanceof Person) {
                if (this.id == ((Person) obj).getId())
                    if (this.getFirstName().equals(((Person) obj).getFirstName()))
                        if (this.getLastName().equals(((Person) obj).getLastName()))
                            if (this.getSexId() == ((Person) obj).getSexId())
                                if (this.getBirthday().equals(((Person) obj).getBirthday())) return true;
            }
        }
        return false;
    }
}
