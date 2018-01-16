package pro.kretov.main;

import pro.kretov.common.Result;
import pro.kretov.common.SDF;
import pro.kretov.db.DAO.user.UserDataDAO;
import pro.kretov.db.DAO.user.UserDataDAOImpl;
import pro.kretov.db.POJO.UserData;
import pro.kretov.db.connectionManager.ConnectionManagerImpl;

import java.sql.Date;
import java.util.List;

import static pro.kretov.common.PersistType.NEW;
import static pro.kretov.common.PersistType.RESTORE;

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
                    new Date(SDF.getDate("2018-01-01").getResult().getTime())
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
