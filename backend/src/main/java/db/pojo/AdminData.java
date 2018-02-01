package db.pojo;

public interface AdminData {
    UserData getUser();

    void setUser(UserData user);

    int getId();

    void setId(int id);

    int getUserId();

    void setUserId(int userId);
}
