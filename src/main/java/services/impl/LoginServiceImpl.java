package services.impl;

import common.Result;
import db.dao.user.UserDataDAO;
import db.dao.user.UserDataDAOImpl;
import db.pojo.UserData;
import db.connectionManager.ConnectionManagerImpl;
import services.LoginService;

public class LoginServiceImpl implements LoginService {

    private UserDataDAO userDataDAO = new UserDataDAOImpl(ConnectionManagerImpl.getInstance());

    public boolean checkAuth(String login, String password) {
        Result<UserData> result = userDataDAO.getByLogin(login);
        return result.isSuccess() && result.get().getPassword().equals(password);
    }

}
