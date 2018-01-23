package consoleTest.get;

import common.Result;
import db.DAO.user.UserDataDAO;
import db.DAO.user.UserDataDAOImpl;
import db.POJO.UserData;
import db.connectionManager.ConnectionManagerImpl;

public class TestGetBy {
    public static void main(String[] args) {
        {
            System.out.println("UserData");
            UserDataDAO userDataDAO = new UserDataDAOImpl(ConnectionManagerImpl.getInstance());
            Result<UserData> result = userDataDAO.getById(1);
            if (result.isSuccess()) {
                System.out.println(result.get());
            } else {
                System.out.println(result.getMessage());
            }
        }
        {
            System.out.println("UserData");
            UserDataDAO userDataDAO = new UserDataDAOImpl(ConnectionManagerImpl.getInstance());
            Result<UserData> result = userDataDAO.getByLogin("alex");
            if (result.isSuccess()) {
                System.out.println(result.get());
            } else {
                System.out.println(result.getMessage());
            }
        }
    }
}
