package services.impl;

import common.Result;
import db.dao.user.UserDataDAO;
import db.dao.user.UserDataDAOImpl;
import db.pojo.UserData;
import org.junit.Test;

public class LoginServiceImplTest {

    @Test
    public void checkAuth() {
        UserDataDAO userDataDAO = new UserDataDAOImpl();

        Result<UserData> result = userDataDAO.getByLogin("alex");

        if (result.isSuccess()) {
            System.out.println(result.get().getPassword());
        }

    }
}