package services;

import common.Result;
import db.entities.UserData;

import java.io.Serializable;
import java.util.List;

public interface UserService extends Serializable {

    List<UserData> getUserList();

    Result<UserData> getUserByLogin(String login);
}
