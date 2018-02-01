package db.pojo;

public interface Person {
    int getId();

    void setId(int id);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getBirthday();

    void setBirthday(String birthday);

    Sex getSex();

    void setSex(Sex sex);

    int getSexId();

    void setSexId(int sexId);

    @Override
    String toString();
}
