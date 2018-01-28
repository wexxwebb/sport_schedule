package services.impl;

import common.RegisterDataCheck;
import common.Result;
import db.dao.user.UserDataDAO;
import db.dao.user.UserDataDAOImpl;
import db.pojo.UserData;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static junit.framework.TestCase.assertTrue;

public class RegisterServiceImplTest {

    private Logger logger = Logger.getLogger(RegisterServiceImplTest.class);

    @Test
    public void checkData() {
        UserDataDAO userDataDAO = (UserDataDAO) Proxy.newProxyInstance(
                UserDataDAOImpl.class.getClassLoader(),
                UserDataDAOImpl.class.getInterfaces(),
                (proxy, method, args) -> {
                    UserDataDAO trueUserDataDAO = new UserDataDAOImpl();
                    if (method.getName().equals("getByLogin")) {
                        return new Result<UserData>(null, false, "Test");
                    }
                    return method.invoke(trueUserDataDAO, args);
                }
        );

        RegisterServiceImpl registerServiceImpl = new RegisterServiceImpl();
        //registerServiceImpl.setUserDataDAO(userDataDAO);

        try {

            Method method = registerServiceImpl.getClass().getDeclaredMethod(
                    "checkData", String.class, String.class, String.class,
                    String.class, String.class, String.class, String.class
            );
            method.setAccessible(true);
            RegisterDataCheck regCheck = (RegisterDataCheck) method.invoke(
                    registerServiceImpl, new Object[]{"alex", "password", "password", "Alex", "Webb", "1", "1989-01-08"});

            assertTrue(regCheck.isValid());
            logger.info("Checked");

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}