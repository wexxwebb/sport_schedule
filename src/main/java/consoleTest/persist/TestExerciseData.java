package consoleTest.persist;

import common.Result;
import db.DAO.exerciseData.ExerciseDataDAO;
import db.DAO.exerciseData.ExerciseDataDAOImpl;
import db.POJO.ExerciseData;
import db.connectionManager.ConnectionManagerImpl;

import java.util.List;

import static common.PersistType.NEW;
import static common.PersistType.RESTORE;

public class TestExerciseData {
    public static void main(String[] args) {

        ExerciseDataDAO exerciseDataDAO = new ExerciseDataDAOImpl(
                ConnectionManagerImpl.getInstance()
        );

        {
            ExerciseData exerciseData = new ExerciseData("Жим лежа");
            Result<String> result;
            if ((result = exerciseDataDAO.persist(exerciseData, NEW)).isSuccess()) {
                System.out.println(result.getResult());
            } else {
                System.out.println(result.getMessage());
            }
        }

        {
            ExerciseData exerciseData = new ExerciseData(25,"Гиперэкстензия");
            Result<String> result;
            if ((result = exerciseDataDAO.persist(exerciseData, RESTORE)).isSuccess()) {
                System.out.println(result.getResult());
            } else {
                System.out.println(result.getMessage());
            }
        }

        Result<List<ExerciseData>> result = exerciseDataDAO.getAll();
        if (result != null && result.isSuccess()) {
            for (ExerciseData exerciseData : result.getResult()) {
                System.out.println(exerciseData);
            }
        } else {
            System.out.println(result.getMessage());
        }

    }
}
