package db.dao.user;

import common.InsertType;
import common.Result;
import db.pojo.UserData;

import java.io.Serializable;
import java.util.List;

public interface UserDataDAO extends Serializable {

    Result<List<UserData>> getAll();

    Result<String> insert(UserData user, InsertType insertType);

    Result<UserData> getById(int id);

    Result<UserData> getByLogin(String login);
}
