package db.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AdminData {

    private int id;
    private UserData user;
    private int userId;

    public AdminData() {
    }

    public AdminData(int userId) {
        this.userId = userId;
    }

    public AdminData(int id, UserData user) {
        this.id = id;
        this.user = user;
    }

    public AdminData(int id, int userId) {
        this.id = id;
        this.userId = userId;
    }

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    @Override
    public String toString() {
        return "AdminData{" +
                "id=" + id +
                ", userId=" + userId +
                '}';
    }
}
