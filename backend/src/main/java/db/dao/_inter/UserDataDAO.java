package db.dao._inter;

import common.InsertType;
import db.entities.Impl.UserDataImpl;
import db.entities._inter.UserData;

import java.io.Serializable;
import java.util.List;

public interface UserDataDAO extends Serializable {

    List<UserData> getAll();

    UserDataImpl insert(UserDataImpl user, InsertType insertType);

    UserDataImpl getById(long id);

    UserDataImpl getByLogin(String login);
}
