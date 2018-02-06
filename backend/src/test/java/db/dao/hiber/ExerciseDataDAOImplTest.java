package db.dao.hiber;

import db.dao._inter.ExerciseDataDAO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class ExerciseDataDAOImplTest {

    private static ApplicationContext context;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext(
                "spring-config.xml", "database.xml");
    }

    @Test
    public void searchByName() {
        ExerciseDataDAO exerciseDataDAO = context.getBean(ExerciseDataDAOImpl.class);
        exerciseDataDAO.searchByName("Гип").forEach(System.out::println);
    }
}