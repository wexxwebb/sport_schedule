package services.impl;

import common.Result;
import db.dao.user.UserDataDAO;
import db.dao.user.UserDataDAOImpl;
import db.pojo.UserData;
import db.connectionManager.ConnectionManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

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
