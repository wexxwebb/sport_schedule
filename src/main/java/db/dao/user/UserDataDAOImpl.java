package db.dao.user;

import common.InsertType;
import common.Log;
import common.Result;
import db.connectionManager.ConnectionManager;
import db.pojo.UserData;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static common.InsertType.NEW;
import static common.InsertType.RESTORE;

public class UserDataDAOImpl implements UserDataDAO {

    private static Logger logger = Logger.getLogger(UserDataDAOImpl.class);
    private ConnectionManager connectionManager;

    public UserDataDAOImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Result<List<UserData>> getAll() {
        int retry = 0;
        while (true) {
            try {
                Connection connection = connectionManager.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(
                        "SELECT id," +
                                "person_id, " +
                                "user_login, " +
                                "user_password, " +
                                "state_id, " +
                                "date_reg " +
                                "FROM user_data"
                );

                List<UserData> userDataList = new ArrayList<>();
                while (resultSet.next()) {
                    UserData user = new UserData(
                            resultSet.getInt("id"),
                            resultSet.getInt("person_id"),
                            resultSet.getString("user_login"),
                            resultSet.getString("user_password"),
                            resultSet.getInt("state_id"),
                            resultSet.getObject("date_reg").toString()
                    );
                    userDataList.add(user);
                }
                connection.close();
                return new Result<>(userDataList, true, "Success");
            } catch (ClassNotFoundException e) {
                return new Result<>(null, false, e.getMessage());
            } catch (SQLException e) {
                retry++;
                if (retry > 5) return new Result<>(null, false, e.getMessage());
            }
        }
    }

    @Override
    public Result<String> insert(UserData user, InsertType insertType) {
        int retry = 0;
        while (true) {
            try {
                Connection connection = connectionManager.getConnection();
                PreparedStatement preparedStatement = null;
                if (insertType == NEW) {
                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO user_data (person_id, user_login, user_password, state_id) " +
                                    "VALUES (?, ?, ?, ?)"
                    );
                    preparedStatement.setInt(1, user.getPersonId());
                    preparedStatement.setString(2, user.getLogin());
                    preparedStatement.setString(3, user.getPassword());
                    preparedStatement.setInt(4, user.getStateId());

                } else if (insertType == RESTORE) {
                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO user_data (id, person_id, user_login, user_password, state_id) " +
                                    "VALUES (?, ?, ?, ?, ?)"
                    );
                    preparedStatement.setInt(1, user.getPersonId());
                    preparedStatement.setInt(2, user.getPersonId());
                    preparedStatement.setString(3, user.getLogin());
                    preparedStatement.setString(4, user.getPassword());
                    preparedStatement.setInt(5, user.getStateId());
                }
                preparedStatement.addBatch();
                int[] counts = preparedStatement.executeBatch();

                connection.close();
                logger.debug(new Log("Insertion", user));
                return new Result<>(
                        "Success",
                        true, String.format("Inserted %d lines",
                        counts[0])
                );

            } catch (ClassNotFoundException e) {
                return new Result<>(null, false, e.getMessage());
            } catch (SQLException e) {
                retry++;
                if (retry > 5) return new Result<>(null, false, e.getMessage());
                logger.error(new Log(e, user));
            }
        }
    }

    @Override
    public Result<UserData> getById(int id) {
        int retry = 0;
        while (true) {
            try {
                Connection connection = connectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT id," +
                                "person_id, " +
                                "user_login, " +
                                "user_password, " +
                                "state_id, " +
                                "date_reg " +
                                "FROM user_data WHERE id = ?"
                );
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    UserData userData = new UserData(
                            resultSet.getInt("id"),
                            resultSet.getInt("person_id"),
                            resultSet.getString("user_login"),
                            resultSet.getString("user_password"),
                            resultSet.getInt("state_id"),
                            resultSet.getObject("date_reg").toString()
                    );
                    return new Result<>(userData, true, "Success");
                } else {
                    return new Result<>(null, false, String.format("User with id = %d not found", id));
                }
            } catch (ClassNotFoundException e) {
                return new Result<>(null, false, e.getMessage());
            } catch (SQLException e) {
                retry++;
                if (retry > 5) return new Result<>(null, false, e.getMessage());
            }
        }
    }

    @Override
    public Result<UserData> getByLogin(String login) {
        int retry = 0;
        while (true) {
            try {
                Connection connection = connectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT id," +
                                "person_id, " +
                                "user_login, " +
                                "user_password, " +
                                "state_id, " +
                                "date_reg " +
                                "FROM user_data WHERE user_login = ?"
                );
                preparedStatement.setString(1, login);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    UserData userData = new UserData(
                            resultSet.getInt("id"),
                            resultSet.getInt("person_id"),
                            resultSet.getString("user_login"),
                            resultSet.getString("user_password"),
                            resultSet.getInt("state_id"),
                            resultSet.getObject("date_reg").toString()
                    );
                    return new Result<>(userData, true, "Success");
                } else {
                    return new Result<>(null, false, String.format("User with login = %s not found", login));
                }
            } catch (ClassNotFoundException e) {
                return new Result<>(null, false, e.getMessage());
            } catch (SQLException e) {
                retry++;
                if (retry > 5) return new Result<>(null, false, e.getMessage());
            }
        }
    }

}
