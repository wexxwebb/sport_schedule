package db.dao._interfaces;

import common.InsertType;
import common.Result;
import db.entities.Impl.UserDataImpl;
import db.entities.inter.UserData;

import java.io.Serializable;
import java.util.List;

public interface UserDataDAO extends Serializable {

    Result<List<UserData>> getAll();

    UserDataImpl insert(UserDataImpl user, InsertType insertType);

    UserDataImpl getById(long id);

    UserDataImpl getByLogin(String login);
}
