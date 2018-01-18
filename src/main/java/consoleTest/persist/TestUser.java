package consoleTest.persist;

import common.Result;
import common.SDF;
import db.DAO.user.UserDataDAO;
import db.DAO.user.UserDataDAOImpl;
import db.POJO.UserData;
import db.connectionManager.ConnectionManagerImpl;

import java.util.List;

import static common.PersistType.NEW;
import static common.PersistType.RESTORE;

public class TestUser {
    public static void main(String[] args) {
        UserDataDAO userDataDAO =
                new UserDataDAOImpl(ConnectionManagerImpl.getInstance());
        {
            UserData user = new UserData(
                    1,
                    "alex",
                    "passsword",
                    1
            );
            Result<String> result = userDataDAO.persist(user, NEW);
            System.out.println(result.getMessage());
        }

        {
            UserData user = new UserData(
                    25,
                    25,
                    "nat",
                    "passsword",
                    25,
                    "2018-01-01"
            );
            Result<String> result = userDataDAO.persist(user, RESTORE);
            System.out.println(result.getMessage());

        }
        Result<List<UserData>> users = userDataDAO.getAll();
        if (users != null && users.isSuccess()) {
            for (UserData user : users.getResult()) {
                System.out.println(user);
            }
        } else {
            System.out.println(users.getMessage());
        }
    }
}
