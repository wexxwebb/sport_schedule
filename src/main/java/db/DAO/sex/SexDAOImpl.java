package db.DAO.sex;

import common.PersistType;
import common.Result;
import db.POJO.Sex;
import db.connectionManager.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static common.PersistType.NEW;
import static common.PersistType.RESTORE;


public class SexDAOImpl implements SexDAO {

    private ConnectionManager connectionManager;

    public SexDAOImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Result<List<Sex>> getAll() {
        int retry = 0;
        while (true) {
            try {
                Connection connection = connectionManager.getConnection();
                Statement statement = connection.createStatement();

                ResultSet result = statement.executeQuery(
                        "SELECT " +
                                "id, " +
                                "sex " +
                                "FROM sex"
                );

                List<Sex> sexList = new ArrayList<>();
                while (result.next()) {
                    Sex sex = new Sex(
                            result.getInt("id"),
                            result.getString("sex")
                    );
                    sexList.add(sex);
                }
                return new Result<>(sexList, true, "Success");
            } catch (ClassNotFoundException e) {
                return new Result<>(null, false, e.getMessage());
            } catch (SQLException e) {
                retry++;
                if (retry > 5) return new Result<>(null, false, e.getMessage());
            }
        }
    }

    @Override
    public Result<String> persist(Sex sex, PersistType persistType) {
        int retry = 0;
        while (true) {
            try {
                Connection connection = connectionManager.getConnection();
                PreparedStatement preparedStatement = null;
                if (persistType == NEW) {
                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO sex (sex) VALUES (?)"
                    );

                    preparedStatement.setString(1, sex.getSex());
                } else if (persistType == RESTORE) {
                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO sex (id, sex) VALUES (?, ?)"
                    );
                    preparedStatement.setInt(1, sex.getId());
                    preparedStatement.setString(2, sex.getSex());
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
}
