package pro.kretov.main;

import pro.kretov.common.Result;
import pro.kretov.common.SDF;
import pro.kretov.db.DAO.training.TrainingDAO;
import pro.kretov.db.DAO.training.TrainingDAOImpl;
import pro.kretov.db.POJO.Training;
import pro.kretov.db.connectionManager.ConnectionManagerImpl;

import java.sql.Date;
import java.util.List;

import static pro.kretov.common.PersistType.NEW;
import static pro.kretov.common.PersistType.RESTORE;

public class TestTraining {
    public static void main(String[] args) {
        TrainingDAO trainingDAO =
                new TrainingDAOImpl(ConnectionManagerImpl.getInstance());
        {
            Training training = new Training(
                    1,
                    new Date(SDF.getDate("2018-01-18").getResult().getTime())
            );
            Result<String> result = trainingDAO.persist(training, NEW);
            System.out.println(result.getMessage());

        }

        {
            Training training = new Training(
                    25,
                    25,
                    new Date(SDF.getDate("2018-01-19").getResult().getTime()),
                    new Date(SDF.getDate("2018-01-30").getResult().getTime())
            );
            Result<String> result = trainingDAO.persist(training, RESTORE);
            System.out.println(result.getMessage());

        }
        Result<List<Training>> trainings = trainingDAO.getAll();
        if (trainings != null && trainings.isSuccess()) {
            for (Training training : trainings.getResult()) {
                System.out.println(training);
            }
        } else {
            System.out.println(trainings.getMessage());
        }
    }
}
