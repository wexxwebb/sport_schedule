package db.entities.Impl;


import db.entities.inter.Training;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;

@XmlRootElement
public class TrainingImpl implements Training {

    private long id;
    private transient UserDataImpl userData;
    private Date createDate;
    private Date trainingDate;

    public TrainingImpl() {
    }

    public TrainingImpl(long id, UserDataImpl userData, Date createDate, Date trainingDate) {
        this.id = id;
        this.userData = userData;
        this.createDate = createDate;
        this.trainingDate = trainingDate;
    }

    public TrainingImpl(UserDataImpl userData, Date trainingDate) {
        this.userData = userData;
        this.trainingDate = trainingDate;
    }

    @Override
    public UserDataImpl getUser() {
        return userData;
    }

    @Override
    public void setUser(UserDataImpl userData) {
        this.userData = userData;
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
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public Date getTrainingDate() {
        return trainingDate;
    }

    @Override
    public void setTrainingDate(Date trainingDate) {
        this.trainingDate = trainingDate;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\": " + id +
                ", \"createDate\": " + "\"" + createDate + "\"" +
                ", \"trainingDate\": " + "\"" + trainingDate + "\"" +
                "}";
    }
}
