package db.dao.exercise;

import common.InsertType;
import common.Log;
import common.Result;
import db.connectionManager.ConnectionManagerImpl;
import db.pojo.Exercise;
import db.connectionManager.ConnectionManager;
import db.pojo.ExerciseData;
import db.pojo.Training;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static common.InsertType.NEW;
import static common.InsertType.RESTORE;

@Component
public class ExerciseDAOImpl implements ExerciseDAO {

    private static Logger logger = Logger.getLogger(ExerciseDAOImpl.class);

    private ConnectionManager connectionManager;

    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    public void setConnectionManager() {
        this.connectionManager = ConnectionManagerImpl.getInstance();
    }

    public ExerciseDAOImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Result<List<Exercise>> getAll() {
        int retry = 0;
        while (true) {
            try {
                Connection connection = connectionManager.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(
                        "SELECT id," +
                                "exercise_id, " +
                                "training_id, " +
                                "approach, " +
                                "repetition, " +
                                "weigth " +
                                "FROM exercise"
                );

                List<Exercise> exerciseList = new ArrayList<>();
                while (resultSet.next()) {
                    Exercise exercise = new Exercise(
                            resultSet.getInt("id"),
                            resultSet.getInt("exercise_id"),
                            resultSet.getInt("training_id"),
                            resultSet.getInt("approach"),
                            resultSet.getInt("repetition"),
                            resultSet.getInt("weigth")
                    );
                    exerciseList.add(exercise);
                }
                return new Result<>(exerciseList, true, "Success");
            } catch (ClassNotFoundException e) {
                return new Result<>(null, false, e.getMessage());
            } catch (SQLException e) {
                retry++;
                if (retry > 5) return new Result<>(null, false, e.getMessage());
            }
        }
    }

    @Override
    public Result<List<Exercise>> getByTrainingId(int trainingId) {
        int retry = 0;
        while (true) {
            try {
                Connection connection = connectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT e.id, ed.id AS ed_id, e.exercise_id, e.training_id, " +
                                "t.create_date, t.training_date, t.user_id, " +
                                "e.approach, e.repetition, e.weigth, ed.name AS name " +
                                "FROM exercise e JOIN exercise_data ed ON e.exercise_id = ed.id " +
                                "JOIN training t ON e.training_id = t.id " +
                                "WHERE e.training_id = ?"
                );
                preparedStatement.setInt(1,trainingId);
                preparedStatement.addBatch();
                ResultSet resultSet = preparedStatement.executeQuery();

                List<Exercise> exerciseList = new ArrayList<>();
                while (resultSet.next()) {
                    ExerciseData exerciseData = new ExerciseData(
                            resultSet.getInt("ed_id"),
                            resultSet.getString("name")
                    );

                    Training training = new Training(
                            resultSet.getInt("training_id"),
                            resultSet.getInt("user_id"),
                            resultSet.getString("create_date"),
                            resultSet.getString("training_date")
                    );

                    Exercise exercise = new Exercise(
                            resultSet.getInt("id"),
                            exerciseData,
                            training,
                            resultSet.getInt("approach"),
                            resultSet.getInt("repetition"),
                            resultSet.getInt("weigth")
                    );
                    exerciseList.add(exercise);
                }
                return new Result<>(exerciseList, true, "Success");

            } catch (ClassNotFoundException e) {
                logger.error(e);
                return new Result<>(null, false, "Невозможно соединение с базой данных");
            } catch (SQLException e) {
                retry++;
                if (retry > 5) return new Result<>(null, false, "При запросе произошла ошибка");
                logger.error(new Log(e, "trainingId = " + trainingId));
            }
        }
    }

    @Override
    public Result<Exercise> insert(Exercise exercise, InsertType insertType) {
        int retry = 0;
        while (true) {
            try {
                Connection connection = connectionManager.getConnection();
                String sql = null;

                PreparedStatement preparedStatement = null;
                if (insertType == NEW) {
                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO exercise (exercise_id, training_id, approach, repetition, weigth) " +
                                    "VALUES (?, ?, ?, ?, ?) RETURNING id"
                    );
                    preparedStatement.setInt(1, exercise.getExerciseId());
                    preparedStatement.setInt(2, exercise.getTrainingId());
                    preparedStatement.setInt(3, exercise.getApproach());
                    preparedStatement.setInt(4, exercise.getRepetition());
                    preparedStatement.setInt(5, exercise.getWeigth());

                } else if (insertType == RESTORE) {
                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO exercise (id, exercise_id, training_id, approach, repetition, weigth) " +
                                    "VALUES (?, ?, ?, ?, ?, ?)"
                    );
                    preparedStatement.setInt(1, exercise.getId());
                    preparedStatement.setInt(2, exercise.getExerciseId());
                    preparedStatement.setInt(3, exercise.getTrainingId());
                    preparedStatement.setInt(4, exercise.getApproach());
                    preparedStatement.setInt(5, exercise.getRepetition());
                    preparedStatement.setInt(6, exercise.getWeigth());
                }

                preparedStatement.addBatch();
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    exercise.setId(resultSet.getInt("id"));
                }

                return new Result<>(exercise, true, "Inserted");

            } catch (ClassNotFoundException e) {
                logger.error(e);
                return new Result<>(null, false, e.getMessage());
            } catch (SQLException e) {
                retry++;
                if (retry > 5) return new Result<>(null, false, e.getMessage());
                logger.error(new Log(e, exercise, "retry = " + retry));
            }
        }
    }
}
