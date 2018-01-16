package pro.kretov.db.POJO;


import java.sql.Date;

public class Training {

    private int id;
    private int userId;
    private UserData user;
    private java.sql.Date createDate;
    private java.sql.Date trainingDate;

    public Training(int id, int userId, Date createDate, Date trainingDate) {
        this.id = id;
        this.userId = userId;
        this.createDate = createDate;
        this.trainingDate = trainingDate;
    }

    public Training(int id, UserData user, Date createDate, Date trainingDate) {
        this.id = id;
        this.user = user;
        this.createDate = createDate;
        this.trainingDate = trainingDate;
    }

    public Training(int userId, Date trainingDate) {
        this.userId = userId;
        this.trainingDate = trainingDate;
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


    public java.sql.Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(java.sql.Date createDate) {
        this.createDate = createDate;
    }


    public java.sql.Date getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(java.sql.Date trainingDate) {
        this.trainingDate = trainingDate;
    }

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", userId=" + userId +
                ", createDate=" + createDate +
                ", trainingDate=" + trainingDate +
                '}';
    }
}
