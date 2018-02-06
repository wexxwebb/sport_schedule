package db.dao.hiber;

import common.SDF;
import db.dao._inter.PersonDAO;
import db.dao._inter.SexDAO;
import db.dao._inter.TrainingDAO;
import db.dao._inter.UserDataDAO;
import db.entities.Impl.PersonImpl;
import db.entities.Impl.SexImpl;
import db.entities.Impl.TrainingImpl;
import db.entities.Impl.UserDataImpl;
import db.entities._inter.Training;
import db.entities._inter.UserData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import util.CustomPasswordEncoder;

import java.sql.Date;

import static common.InsertType.NEW;

public class UserDataDAOImplTest {

    private static ApplicationContext context;
    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext(
                "database.xml", "spring-config.xml");
    }

    @Test
    public void getByLogin() {
        SexDAO sexDAO = context.getBean(SexDAOImpl.class);
        PersonDAO personDAO = context.getBean(PersonDAOImpl.class);
        UserDataDAO userDataDAO = context.getBean(UserDataDAOImpl.class);
        TrainingDAO trainingDAO = context.getBean(TrainingDAOImpl.class);
        PasswordEncoder passwordEncoder = context.getBean(CustomPasswordEncoder.class);

        SexImpl sex = new SexImpl(1, "Мужской");
        PersonImpl person = new PersonImpl("Александр", "Кретов",
                new Date(SDF.getDate("1989-01-08").get().getTime()), sex);
        UserDataImpl userData = new UserDataImpl("alex", passwordEncoder.encode("alex"),
                new Date(SDF.getDate("2018-01-01").get().getTime()), true, "ROLE_ADMIN");
        TrainingImpl training = new TrainingImpl(userData, new Date((new java.util.Date()).getTime()),
                new Date((new java.util.Date()).getTime()));

        userData.setPerson(person);
        person.setUserData(userData);

        sex = sexDAO.insert(sex);
        person = personDAO.insert(person, NEW);
        userData = userDataDAO.insert(userData, NEW);
        training = trainingDAO.insert(training);

        UserData userFromDB = userDataDAO.getByLogin("alex");
        System.out.println(training);
        System.out.println(userFromDB);

    }

    @Test
    public void getById() {
        UserDataDAO userDataDAO = context.getBean(UserDataDAO.class);
        SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
        Session session = sessionFactory.openSession();
        for (Training training : userDataDAO.getById(1).getTrainingCollection()) {
            System.out.println(training);
        }
        session.close();
    }
}
























