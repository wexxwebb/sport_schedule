package pro.kretov.main;

import pro.kretov.common.PersistType;
import pro.kretov.common.Result;
import pro.kretov.db.DAO.exerciseData.ExerciseDataDAO;
import pro.kretov.db.DAO.exerciseData.ExerciseDataDAOImpl;
import pro.kretov.db.POJO.ExerciseData;
import pro.kretov.db.connectionManager.ConnectionManagerImpl;

import java.util.List;

import static pro.kretov.common.PersistType.NEW;
import static pro.kretov.common.PersistType.RESTORE;

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
