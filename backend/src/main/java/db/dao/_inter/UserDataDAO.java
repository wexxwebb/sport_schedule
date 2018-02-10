package db.dao._inter;

import db.dao.excep.DataIsNotAvailableException;
import db.entities.Impl.UserDataImpl;

import java.io.Serializable;

public interface UserDataDAO extends Serializable {

    UserDataImpl insert(UserDataImpl user) throws DataIsNotAvailableException;

    UserDataImpl getById(long id) throws DataIsNotAvailableException;

    UserDataImpl getByLogin(String login) throws DataIsNotAvailableException;
}
