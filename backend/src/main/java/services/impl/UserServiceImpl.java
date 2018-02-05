package services.impl;

import common.Logged;
import common.Result;
import db.dao._interfaces.UserDataDAO;
import db.entities.inter.UserData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services._interfaces.UserService;

import java.sql.Date;
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
    public db.entities.Impl.UserDataImpl getUserByLogin(String login) {
        return userDataDAO.getByLogin(login);
    }

}
