package db.pojo;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Training {

    private int id;
    private int userId;
    private UserData user;
    private String createDate;
    private String trainingDate;

    public Training() {
    }

    public Training(int id, int userId, String createDate, String trainingDate) {
        this.id = id;
        this.userId = userId;
        this.createDate = createDate;
        this.trainingDate = trainingDate;
    }

    public Training(int id, UserData user, String createDate, String trainingDate) {
        this.id = id;
        this.user = user;
        this.createDate = createDate;
        this.trainingDate = trainingDate;
    }

    public Training(int userId, String trainingDate) {
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


    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }


    public String getTrainingDate() {
        return trainingDate;
    }

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
