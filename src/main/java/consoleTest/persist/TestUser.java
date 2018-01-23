package consoleTest.persist;

import common.Result;
import db.dao.user.UserDataDAO;
import db.dao.user.UserDataDAOImpl;
import db.pojo.UserData;
import db.connectionManager.ConnectionManagerImpl;

import java.util.List;

import static common.InsertType.NEW;
import static common.InsertType.RESTORE;

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
            Result<String> result = userDataDAO.insert(user, NEW);
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
            Result<String> result = userDataDAO.insert(user, RESTORE);
            System.out.println(result.getMessage());

        }
        Result<List<UserData>> users = userDataDAO.getAll();
        if (users != null && users.isSuccess()) {
            for (UserData user : users.get()) {
                System.out.println(user);
            }
        } else {
            System.out.println(users.getMessage());
        }
    }
}
