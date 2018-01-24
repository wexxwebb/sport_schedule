package services.impl;

import common.Result;
import db.dao.user.UserDataDAO;
import db.dao.user.UserDataDAOImpl;
import db.pojo.UserData;
import db.connectionManager.ConnectionManager;
import db.connectionManager.ConnectionManagerImpl;

public class AuthService {

    private ConnectionManager connectionManager;

    public AuthService() {
        connectionManager = ConnectionManagerImpl.getInstance();
    }

    public boolean auth(String login, String password) {
        UserDataDAO userDataDAO =
                new UserDataDAOImpl();
        Result<UserData> result = userDataDAO.getByLogin(login);
        if (result.isSuccess()) {
            if (result.get().getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

}
