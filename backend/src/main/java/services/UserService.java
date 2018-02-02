package services;

import db.pojo.UserData;

import java.io.Serializable;
import java.util.List;

public interface UserService extends Serializable {

    List<UserData> getUserList();
}
