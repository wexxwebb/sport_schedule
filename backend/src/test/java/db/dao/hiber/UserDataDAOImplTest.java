package db.dao.hiber;

import common.SDF;
import db.dao._interfaces.PersonDAO;
import db.dao._interfaces.SexDAO;
import db.dao._interfaces.UserDataDAO;
import db.entities.Impl.PersonImpl;
import db.entities.Impl.SexImpl;
import db.entities.Impl.UserDataImpl;
import db.entities.inter.UserData;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Date;

import static common.InsertType.NEW;
import static org.junit.Assert.*;

public class UserDataDAOImplTest {

    private static ApplicationContext context;
    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext(
                "database.xml");
    }

    @Test
    public void getByLogin() {
        SexDAO sexDAO = context.getBean(SexDAOImpl.class);
        PersonDAO personDAO = context.getBean(PersonDAOImpl.class);
        UserDataDAO userDataDAO = context.getBean(UserDataDAOImpl.class);
        SexImpl sex = new SexImpl(1, "Мужской");
        PersonImpl person = new PersonImpl("Александр", "Кретов",
                new Date(SDF.getDate("1989-01-08").get().getTime()), sex);
        UserDataImpl userData = new UserDataImpl("alex", "alex",
                new Date(SDF.getDate("2018-01-01").get().getTime()));

        userData.setPerson(person);
        person.setUserData(userData);

        sex = sexDAO.insert(sex);
        person = personDAO.insert(person, NEW);
        userData = userDataDAO.insert(userData, NEW);

        UserData userFromDB = userDataDAO.getByLogin("alex");
        System.out.println(userFromDB);



    }

}
























