package db.dao.exercise;

import common.InsertType;
import common.Result;
import db.pojo.Exercise;
import db.connectionManager.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static common.InsertType.NEW;
import static common.InsertType.RESTORE;

public class ExerciseDAOImpl implements ExerciseDAO {

    ConnectionManager connectionManager;

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

                List<Exercise> personList = new ArrayList<>();
                while (resultSet.next()) {
                    Exercise exercise = new Exercise(
                            resultSet.getInt("id"),
                            resultSet.getInt("exercise_id"),
                            resultSet.getInt("training_id"),
                            resultSet.getInt("approach"),
                            resultSet.getInt("repetition"),
                            resultSet.getInt("weigth")
                    );
                    personList.add(exercise);
                }
                return new Result<>(personList, true, "Success");
            } catch (ClassNotFoundException e) {
                return new Result<>(null, false, e.getMessage());
            } catch (SQLException e) {
                retry++;
                if (retry > 5) return new Result<>(null, false, e.getMessage());
            }
        }
    }

    @Override
    public Result<String> insert(Exercise exercise, InsertType insertType) {
        int retry = 0;
        while (true) {
            try {
                Connection connection = connectionManager.getConnection();
                String sql = null;

                PreparedStatement preparedStatement = null;
                if (insertType == NEW) {
                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO exercise (exercise_id, training_id, approach, repetition, weigth) " +
                                    "VALUES (?, ?, ?, ?, ?)"
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
                int[] counts = preparedStatement.executeBatch();
                return new Result<>("Success", true, String.format( "Inserted %d lines", counts[0]));

            } catch (ClassNotFoundException e) {
                return new Result<>(null, false, e.getMessage());
            } catch (SQLException e) {
                retry++;
                if (retry > 5) return new Result<>(null, false, e.getMessage());
            }
        }
    }
}
