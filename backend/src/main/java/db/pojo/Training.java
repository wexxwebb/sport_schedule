package db.pojo;

public interface Training {
    UserData getUser();

    void setUser(UserData user);

    int getId();

    void setId(int id);

    int getUserId();

    void setUserId(int userId);

    String getCreateDate();

    void setCreateDate(String createDate);

    String getTrainingDate();

    void setTrainingDate(String trainingDate);

    @Override
    String toString();
}
