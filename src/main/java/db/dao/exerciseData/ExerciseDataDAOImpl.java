package db.dao.exerciseData;

import common.InsertType;
import common.Result;
import db.pojo.ExerciseData;
import db.connectionManager.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static common.InsertType.NEW;
import static common.InsertType.RESTORE;

public class ExerciseDataDAOImpl implements ExerciseDataDAO {
    private ConnectionManager connectionManager;

    public ExerciseDataDAOImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Result<List<ExerciseData>> getAll() {
        int retry = 0;
        while (true) {
            try {
                Connection connection = connectionManager.getConnection();
                Statement statement = connection.createStatement();

                ResultSet result = statement.executeQuery(
                        "SELECT " +
                                "id, " +
                                "name " +
                                "FROM exercise_data"
                );

                List<ExerciseData> exerciseDataList = new ArrayList<>();
                while (result.next()) {
                    ExerciseData exerciseDataData = new ExerciseData(
                            result.getInt("id"),
                            result.getString("name")
                    );
                    exerciseDataList.add(exerciseDataData);
                }
                return new Result<>(exerciseDataList, true, "Success");
            } catch (ClassNotFoundException e) {
                return new Result<>(null, false, e.getMessage());
            } catch (SQLException e) {
                retry++;
                if (retry > 5) return new Result<>(null, false, e.getMessage());
            }
        }
    }

    @Override
    public Result<String> insert(ExerciseData exerciseDataData, InsertType insertType) {
        int retry = 0;
        while (true) {
            try {
                Connection connection = connectionManager.getConnection();
                PreparedStatement preparedStatement = null;
                if (insertType == NEW) {
                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO exercise_data (name) VALUES (?)"
                    );
                    preparedStatement.setString(1, exerciseDataData.getName());
                } else if (insertType == RESTORE) {
                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO exercise_data (id, name) VALUES (?, ?)"
                    );
                    preparedStatement.setInt(1, exerciseDataData.getId());
                    preparedStatement.setString(2, exerciseDataData.getName());
                }

                preparedStatement.addBatch();
                int[] count = preparedStatement.executeBatch();

                return new Result<>(String.format("Inserted %d lines", count[0]), true, "Success");

            } catch (ClassNotFoundException e) {
                return new Result<>(null, false, e.getMessage());
            } catch (SQLException e) {
                retry++;
                if (retry > 5) return new Result<>(null, false, e.getMessage());
            }
        }
    }

    public Result<List<ExerciseData>> getSearch(String term) {
        int retry = 0;
        while (true) {
            try {
                Connection connection = connectionManager.getConnection();
                Statement statement = connection.createStatement();

                PreparedStatement ps = connection.prepareStatement(
                        "SELECT " +
                                "id, " +
                                "name " +
                                "FROM exercise_data WHERE name ILIKE ?"
                );

                ps.setString(1, "%" + term + "%");
                ps.addBatch();

                ResultSet result = ps.executeQuery();

                List<ExerciseData> exerciseDataList = new ArrayList<>();
                while (result.next()) {
                    ExerciseData exerciseDataData = new ExerciseData(
                            result.getInt("id"),
                            result.getString("name")
                    );
                    exerciseDataList.add(exerciseDataData);
                }
                return new Result<>(exerciseDataList, true, "Success");
            } catch (ClassNotFoundException e) {
                return new Result<>(null, false, e.getMessage());
            } catch (SQLException e) {
                retry++;
                if (retry > 5) return new Result<>(null, false, e.getMessage());
            }
        }
    }
}
