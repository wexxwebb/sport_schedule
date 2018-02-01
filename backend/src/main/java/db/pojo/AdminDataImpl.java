package db.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AdminDataImpl implements AdminData {

    private int id;
    private UserData user;
    private int userId;

    public AdminDataImpl() {
    }

    public AdminDataImpl(int userId) {
        this.userId = userId;
    }

    public AdminDataImpl(int id, UserData user) {
        this.id = id;
        this.user = user;
    }

    public AdminDataImpl(int id, int userId) {
        this.id = id;
        this.userId = userId;
    }

    @Override
    public UserData getUser() {
        return user;
    }

    @Override
    public void setUser(UserData user) {
        this.user = user;
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
    public int getUserId() {
        return userId;
    }

    @Override
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
