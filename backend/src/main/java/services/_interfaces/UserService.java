package services._interfaces;

import db.entities.Impl.UserDataImpl;
import db.entities.inter.UserData;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public interface UserService extends Serializable {

    List<UserData> getUserList();

    UserDataImpl getUserByLogin(String login);
}
