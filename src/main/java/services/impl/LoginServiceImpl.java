package services.impl;

import common.Result;
import db.DAO.user.UserDataDAO;
import db.DAO.user.UserDataDAOImpl;
import db.POJO.UserData;
import db.connectionManager.ConnectionManagerImpl;
import services.LoginService;

public class LoginServiceImpl implements LoginService {

    private UserDataDAO userDataDAO = new UserDataDAOImpl(ConnectionManagerImpl.getInstance());

    public boolean checkAuth(String login, String password) {
        Result<UserData> result = userDataDAO.getByLogin(login);
        return result.isSuccess() && result.get().getPassword().equals(password);
    }

}
