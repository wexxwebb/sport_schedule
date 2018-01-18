package services;

import common.Result;
import db.DAO.user.UserDataDAO;
import db.DAO.user.UserDataDAOImpl;
import db.POJO.UserData;
import db.connectionManager.ConnectionManager;
import db.connectionManager.ConnectionManagerImpl;

public class AuthService {

    private ConnectionManager connectionManager;

    public AuthService() {
        connectionManager = ConnectionManagerImpl.getInstance();
    }

    public boolean auth(String login, String password) {
        UserDataDAO userDataDAO =
                new UserDataDAOImpl(connectionManager);
        Result<UserData> result = userDataDAO.getByLogin(login);
        if (result.isSuccess()) {
            if (result.getResult().getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

}
