package services.impl;

import common.Logged;
import common.Result;
import db.dao.user.UserDataDAO;
import db.pojo.UserData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import services.LoginService;

@Component
public class LoginServiceImpl implements LoginService {

    @Logged
    private Logger logger;

    @Autowired
    private UserDataDAO userDataDAO;

    public UserDataDAO getUserDataDAO() {
        return userDataDAO;
    }

    public void setUserDataDAO(UserDataDAO userDataDAO) {
        this.userDataDAO = userDataDAO;
    }

    public boolean checkAuth(String login, String password) {
        Result<UserData> result = userDataDAO.getByLogin(login);
        return result.isSuccess() && result.get().getPassword().equals(password);
    }

}
