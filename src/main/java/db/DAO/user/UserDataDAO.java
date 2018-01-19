package db.DAO.user;

import common.PersistType;
import common.Result;
import db.POJO.UserData;

import java.util.List;

public interface UserDataDAO {
    Result<List<UserData>> getAll();
    Result<String> insert(UserData user, PersistType persistType);
    Result<UserData> getById(int id);
    Result<UserData> getByLogin(String login);
}
