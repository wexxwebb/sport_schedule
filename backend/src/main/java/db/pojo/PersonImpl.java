package db.pojo;


import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "firstName", "lastName", "sexId", "birthday"})

public class PersonImpl implements Person {

    private int id;
    private String firstName;
    private String lastName;
    private String birthday;
    @XmlTransient
    private Sex sex;
    private int sexId;

    public PersonImpl() {
    }

    public PersonImpl(int id, String firstName, String lastName, String birthday, int sexId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.sexId = sexId;
    }

    public PersonImpl(int id, String firstName, String lastName, String birthday, Sex sex) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.sex = sex;
        this.sexId = sex.getId();
    }

    public PersonImpl(String firstName, String lastName, String birthday, int sexId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.sexId = sexId;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
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
    public String getBirthday() {
        return birthday;
    }

    @Override
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public Sex getSex() {
        return sex;
    }

    @Override
    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Override
    public int getSexId() {
        return sexId;
    }

    @Override
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
            if (obj instanceof PersonImpl) {
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
