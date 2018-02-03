package db.entities.Impl;


import db.entities.Training;
import db.entities.UserData;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TrainingImpl implements Training {

    private long id;
    private long userId;
    private UserData user;
    private String createDate;
    private String trainingDate;

    public TrainingImpl() {
    }

    public TrainingImpl(long id, long userId, String createDate, String trainingDate) {
        this.id = id;
        this.userId = userId;
        this.createDate = createDate;
        this.trainingDate = trainingDate;
    }

    public TrainingImpl(long id, UserData user, String createDate, String trainingDate) {
        this.id = id;
        this.user = user;
        this.createDate = createDate;
        this.trainingDate = trainingDate;
    }

    public TrainingImpl(long userId, String trainingDate) {
        this.userId = userId;
        this.trainingDate = trainingDate;
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
    public String getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }


    @Override
    public String getTrainingDate() {
        return trainingDate;
    }

    @Override
    public void setTrainingDate(String trainingDate) {
        this.trainingDate = trainingDate;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\": " + id +
                ", \"userId\": " + userId +
                ", \"createDate\": " + "\"" + createDate + "\"" +
                ", \"trainingDate\": " + "\"" + trainingDate + "\"" +
                "}";
    }
}
