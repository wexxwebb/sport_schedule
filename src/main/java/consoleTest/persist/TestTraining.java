package consoleTest.persist;

import common.Result;
import db.DAO.training.TrainingDAO;
import db.DAO.training.TrainingDAOImpl;
import db.POJO.Training;
import db.connectionManager.ConnectionManagerImpl;

import java.util.List;

import static common.InsertType.NEW;
import static common.InsertType.RESTORE;

public class TestTraining {
    public static void main(String[] args) {
        TrainingDAO trainingDAO =
                new TrainingDAOImpl(ConnectionManagerImpl.getInstance());
        {
            Training training = new Training(
                    1,
                    "2018-01-18"
            );
            Result<String> result = trainingDAO.insert(training, NEW);
            System.out.println(result.getMessage());

        }

        {
            Training training = new Training(
                    25,
                    25,
                    "2018-01-19",
                    "2018-01-30"
            );
            Result<String> result = trainingDAO.insert(training, RESTORE);
            System.out.println(result.getMessage());

        }
        Result<List<Training>> trainings = trainingDAO.getAll();
        if (trainings != null && trainings.isSuccess()) {
            for (Training training : trainings.get()) {
                System.out.println(training);
            }
        } else {
            System.out.println(trainings.getMessage());
        }
    }
}
