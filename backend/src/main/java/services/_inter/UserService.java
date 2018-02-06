package services._inter;

import db.entities.Impl.UserDataImpl;
import db.entities._inter.UserData;

import java.io.Serializable;
import java.util.List;

public interface UserService extends Serializable {

    List<UserData> getUserList();

    UserDataImpl getUserByLogin(String login);
}
