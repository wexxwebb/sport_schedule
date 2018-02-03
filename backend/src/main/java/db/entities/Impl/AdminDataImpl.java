package db.entities.Impl;

import db.entities.AdminData;
import db.entities.UserData;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AdminDataImpl implements AdminData {

    private long id;
    private UserData user;
    private long userId;

    public AdminDataImpl() {
    }

    public AdminDataImpl(long userId) {
        this.userId = userId;
    }

    public AdminDataImpl(long id, UserData user) {
        this.id = id;
        this.user = user;
    }

    public AdminDataImpl(long id, long userId) {
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
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }


    @Override
    public long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(long userId) {
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
