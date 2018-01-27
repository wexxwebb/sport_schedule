package consoleTest.persist;

import common.Result;
import db.dao.exerciseData.ExerciseDataDAO;
import db.dao.exerciseData.ExerciseDataDAOImpl;
import db.pojo.ExerciseData;
import db.connectionManager.ConnectionManagerImpl;

import java.util.List;

import static common.InsertType.NEW;
import static common.InsertType.RESTORE;

public class TestExerciseData {
    public static void main(String[] args) {

        ExerciseDataDAO exerciseDataDAO = new ExerciseDataDAOImpl();

        {
            ExerciseData exerciseData = new ExerciseData("Жим лежа");
            Result<String> result;
            if ((result = exerciseDataDAO.insert(exerciseData, NEW)).isSuccess()) {
                System.out.println(result.get());
            } else {
                System.out.println(result.getMessage());
            }
        }

        {
            ExerciseData exerciseData = new ExerciseData(25,"Гиперэкстензия");
            Result<String> result;
            if ((result = exerciseDataDAO.insert(exerciseData, RESTORE)).isSuccess()) {
                System.out.println(result.get());
            } else {
                System.out.println(result.getMessage());
            }
        }

        Result<List<ExerciseData>> result = exerciseDataDAO.getAll();
        if (result != null && result.isSuccess()) {
            for (ExerciseData exerciseData : result.get()) {
                System.out.println(exerciseData);
            }
        } else {
            System.out.println(result.getMessage());
        }

    }
}
