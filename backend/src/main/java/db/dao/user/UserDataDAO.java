package db.dao.user;

import common.InsertType;
import common.Result;
import db.pojo.UserData;

import java.util.List;

public interface UserDataDAO {

    Result<List<UserData>> getAll();

    Result<String> insert(UserData user, InsertType insertType);

    Result<UserData> getById(int id);

    Result<UserData> getByLogin(String login);
}
