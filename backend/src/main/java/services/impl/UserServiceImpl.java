package services.impl;

import common.Logged;
import common.Result;
import db.dao.UserDataDAO;
import db.pojo.UserData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.UserService;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Logged
    private Logger logger;

    private UserDataDAO userDataDAO;

    @Autowired
    public void setUserDataDAO(UserDataDAO userDataDAO) {
        this.userDataDAO = userDataDAO;
    }

    public UserServiceImpl() {

    }

    public List<UserData> getUserList() {
        Result<List<UserData>> result;
        if ((result = userDataDAO.getAll()).isSuccess()) {
            return result.get();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Result<UserData> getUserByLogin(String login) {
        return userDataDAO.getByLogin(login);
    }

}
