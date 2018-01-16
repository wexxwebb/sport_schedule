package pro.kretov.db.POJO;


import java.sql.Date;

public class Person {

    private int id;
    private String firstName;
    private String lastName;
    private java.sql.Date birthday;
    private Sex sex;
    private int sexId;

    public Person(int id, String firstName, String lastName, Date birthday, int sexId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.sexId = sexId;
    }

    public Person(int id, String firstName, String lastName, Date birthday, Sex sex) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.sex = sex;
        this.sexId = sex.getId();
    }

    public Person(String firstName, String lastName, Date birthday, int sexId) {
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
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
                ", birthday=" + birthday.toString() +
                ", sexId=" + sexId +
                ", sex=" + sex.getSex() +
                '}';
    }
}
