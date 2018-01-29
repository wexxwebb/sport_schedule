package db.dao.state;

import common.InsertType;
import common.Result;
import db.pojo.State;
import db.connectionManager.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static common.InsertType.NEW;
import static common.InsertType.RESTORE;


public class StateDAOImpl implements StateDAO {

    private ConnectionManager connectionManager;

    public StateDAOImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Result<List<State>> getAll() {
        int retry = 0;
        while (true) {
            try {
                Connection connection = connectionManager.getConnection();
                Statement statement = connection.createStatement();

                ResultSet result = statement.executeQuery(
                        "SELECT id, state," +
                                "enabled, role " +
                                "FROM state "
                );

                List<State> sexList = new ArrayList<>();
                while (result.next()) {
                    State state = new State(
                            result.getInt("id"),
                            result.getString("state"),
                            result.getBoolean("enabled"),
                            result.getString("role")
                    );
                    sexList.add(state);
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
    public Result<String> insert(State state, InsertType insertType) {
        int retry = 0;
        while (true) {
            try {
                Connection connection = connectionManager.getConnection();
                PreparedStatement preparedStatement = null;
                if (insertType == NEW) {
                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO state (state) VALUES (?)"
                    );

                    preparedStatement.setString(1, state.getState());
                } else if (insertType == RESTORE){
                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO state (id, state) VALUES (?, ?)"
                    );
                    preparedStatement.setInt(1, state.getId());
                    preparedStatement.setString(2, state.getState());
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
