package db.DAO.state;

import common.PersistType;
import common.Result;
import db.POJO.State;
import db.connectionManager.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static common.PersistType.NEW;
import static common.PersistType.RESTORE;


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
                        "SELECT " +
                                "id, " +
                                "state " +
                                "FROM state "
                );

                List<State> sexList = new ArrayList<>();
                while (result.next()) {
                    State state = new State(
                            result.getInt("id"),
                            result.getString("state")
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
    public Result<String> insert(State state, PersistType persistType) {
        int retry = 0;
        while (true) {
            try {
                Connection connection = connectionManager.getConnection();
                PreparedStatement preparedStatement = null;
                if (persistType == NEW) {
                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO state (state) VALUES (?)"
                    );

                    preparedStatement.setString(1, state.getState());
                } else if (persistType == RESTORE){
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
