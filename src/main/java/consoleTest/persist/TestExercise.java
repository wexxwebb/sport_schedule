package consoleTest.persist;

import common.Result;
import db.DAO.exercise.ExerciseDAO;
import db.DAO.exercise.ExerciseDAOImpl;
import db.POJO.Exercise;
import db.connectionManager.ConnectionManagerImpl;

import java.util.List;

import static common.PersistType.NEW;
import static common.PersistType.RESTORE;

public class TestExercise {
    public static void main(String[] args) {

        ExerciseDAO exerciseDAO =
                new ExerciseDAOImpl(ConnectionManagerImpl.getInstance());

        {
            Exercise exercise = new Exercise(
                    1,
                    1,
                    3,
                    10,
                    5
            );

            Result<String> result;
            if ((result = exerciseDAO.persist(exercise, NEW)).isSuccess()) {
                System.out.println(result.getMessage());
            } else {
                System.out.println(result.getMessage());
            }
        }

        {
            Exercise exercise = new Exercise(
                    25,
                    25,
                    25,
                    3,
                    15,
                    2
            );

            Result<String> result;
            if ((result = exerciseDAO.persist(exercise, RESTORE)).isSuccess()) {
                System.out.println(result.getMessage());
            } else {
                System.out.println(result.getMessage());
            }
        }

        Result<List<Exercise>> result;
        if ((result = exerciseDAO.getAll()).isSuccess()) {
            for (Exercise exercise : result.getResult()) {
                System.out.println(exercise);
            }
        } else {
            System.out.println(result.getMessage());
        }
    }
}