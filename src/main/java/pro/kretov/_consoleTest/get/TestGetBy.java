package pro.kretov._consoleTest.get;

import pro.kretov.common.Result;
import pro.kretov.db.DAO.user.UserDataDAO;
import pro.kretov.db.DAO.user.UserDataDAOImpl;
import pro.kretov.db.POJO.UserData;
import pro.kretov.db.connectionManager.ConnectionManagerImpl;

public class TestGetBy {
    public static void main(String[] args) {
        {
            System.out.println("UserData");
            UserDataDAO userDataDAO = new UserDataDAOImpl(ConnectionManagerImpl.getInstance());
            Result<UserData> result = userDataDAO.getById(1);
            if (result.isSuccess()) {
                System.out.println(result.getResult());
            } else {
                System.out.println(result.getMessage());
            }
        }
        {
            System.out.println("UserData");
            UserDataDAO userDataDAO = new UserDataDAOImpl(ConnectionManagerImpl.getInstance());
            Result<UserData> result = userDataDAO.getByLogin("alex");
            if (result.isSuccess()) {
                System.out.println(result.getResult());
            } else {
                System.out.println(result.getMessage());
            }
        }
    }
}
