package services.impl;

import common.Logged;
import db.dao._inter.UserDataDAO;
import db.entities.Impl.UserDataImpl;
import db.entities._inter.UserData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services._inter.UserService;

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

    public List<UserData> getUserList() {
        return userDataDAO.getAll();
    }

    @Override
    public UserDataImpl getUserByLogin(String login) {
        return userDataDAO.getByLogin(login);
    }

}
