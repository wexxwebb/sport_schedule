package services;

import common.Result;
import db.DAO.user.UserDataDAO;
import db.DAO.user.UserDataDAOImpl;
import db.POJO.UserData;
import db.connectionManager.ConnectionManagerImpl;

public class UserService {

    private static UserDataDAO userDataDAO = new UserDataDAOImpl(ConnectionManagerImpl.getInstance());

    public boolean checkAuth(String login, String password) {
        Result<UserData> result = userDataDAO.getByLogin(login);
        if (result.isSuccess()) {
            if (result.getResult().getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

}
