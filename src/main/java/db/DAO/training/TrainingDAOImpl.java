package db.DAO.training;

import common.PersistType;
import common.Result;
import db.POJO.Training;
import db.connectionManager.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static common.PersistType.NEW;
import static common.PersistType.RESTORE;

public class TrainingDAOImpl implements TrainingDAO {
    ConnectionManager connectionManager;

    public TrainingDAOImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Result<List<Training>> getAll() {
        int retry = 0;
        while (true) {
            try {
                Connection connection = connectionManager.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(
                        "SELECT id," +
                                "user_id, " +
                                "create_date, " +
                                "training_date " +
                                "FROM training"
                );

                List<Training> trainingList = new ArrayList<>();
                while (resultSet.next()) {
                    Training training = new Training(
                            resultSet.getInt("id"),
                            resultSet.getInt("user_id"),
                            resultSet.getObject("create_date").toString(),
                            resultSet.getObject("training_date").toString()
                    );

                    trainingList.add(training);
                }
                return new Result<>(trainingList, true, "Success");
            } catch (ClassNotFoundException e) {
                return new Result<>(null, false, e.getMessage());
            } catch (SQLException e) {
                retry++;
                if (retry > 5) return new Result<>(null, false, e.getMessage());
            }
        }
    }

    @Override
    public Result<String> persist(Training training, PersistType persistType) {
        int retry = 0;
        while (true) {
            try {
                Connection connection = connectionManager.getConnection();
                PreparedStatement preparedStatement = null;
                if (persistType == NEW) {
                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO training (user_id, training_date) " +
                                    "VALUES (?, to_date(?, 'YYYY-MM-DD'))"
                    );
                    preparedStatement.setInt(1, training.getUserId());
                    preparedStatement.setString(2, training.getTrainingDate().toString());

                } else if (persistType == RESTORE) {
                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO training (id, user_id, create_date, training_date) " +
                                    "VALUES (?, ?, to_date(?, 'YYYY-MM-DD'), to_date(?, 'YYYY-MM-DD'))"
                    );
                    preparedStatement.setInt(1, training.getId());
                    preparedStatement.setInt(2, training.getUserId());
                    preparedStatement.setString(3, training.getCreateDate().toString());
                    preparedStatement.setString(4, training.getTrainingDate().toString());

                }

                preparedStatement.addBatch();
                int[] counts = preparedStatement.executeBatch();

                return new Result<>("Success", true, String.format("Inserted %d lines", counts[0]));

            } catch (ClassNotFoundException e) {
                return new Result<>(null, false, e.getMessage());
            } catch (SQLException e) {
                retry++;
                if (retry > 5) return new Result<>(null, false, e.getMessage());
            }
        }
    }
}
