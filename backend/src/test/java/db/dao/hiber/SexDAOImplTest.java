package db.dao.hiber;

import db.dao._inter.SexDAO;
import db.entities.Impl.SexImpl;
import db.entities._inter.Sex;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SexDAOImplTest {

    private static ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext(
                 "database.xml");
    }

    @Test
    public void getAll() {
        SexDAO sexDAO = applicationContext.getBean(SexDAOImpl.class);
        for (Sex sex : sexDAO.getAll()) {
            System.out.println(sex);
        }
    }

    @Test
    public void insert() {
        SexDAO sexDAO = applicationContext.getBean(SexDAOImpl.class);
        SexImpl sex = new SexImpl(1,"Мужской");
        sexDAO.insert(sex);
        sex = new SexImpl("Женский");
        sexDAO.insert(sex);
    }
}