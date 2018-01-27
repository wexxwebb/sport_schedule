package db.dao.training;

import db.pojo.Training;
import org.junit.Test;

import static org.junit.Assert.*;

public class TrainingDAOImplTest {

    @Test
    public void getAll() {
        TrainingDAO trainingDAO = new TrainingDAOImpl();
        for (Training training : trainingDAO.getAll().get()) {
            System.out.println(training);
        }
    }
}