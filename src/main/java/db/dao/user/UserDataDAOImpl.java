package db.dao.user;

import common.InsertType;
import common.Log;
import common.Logged;
import common.Result;
import db.connectionManager.ConnectionManager;
import db.pojo.Person;
import db.pojo.State;
import db.pojo.UserData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static common.InsertType.NEW;
import static common.InsertType.RESTORE;

@Component
public class UserDataDAOImpl implements UserDataDAO {

    @Logged
    private static Logger logger;

    private ConnectionManager connectionManager;

    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    @Autowired
    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public UserDataDAOImpl() {

    }

    @Override
    public Result<List<UserData>> getAll() {
        int retry = 0;
        Connection connection = null;
        while (true) {
            try {
                connection = connectionManager.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(
                        "SELECT ud.id AS user_data_id, person_id, user_login, user_password, " +
                        "state_id, date_reg, first_name, last_name, birthday, sex_id, sex_id, s.enabled, s.role, s.state " +
                                "FROM user_data ud JOIN person p ON ud.person_id = p.id JOIN state s ON ud.state_id = s.id"
                );

                List<UserData> userDataList = new ArrayList<>();
                while (resultSet.next()) {
                    State state = new State(
                            resultSet.getInt("state_id"),
                            resultSet.getString("state"),
                            resultSet.getBoolean("enabled"),
                            resultSet.getString("role"));

                    Person person = new Person(
                            resultSet.getInt("person_id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getObject("birthday").toString(),
                            resultSet.getInt("sex_id"));

                    UserData userData = new UserData(
                            resultSet.getInt("user_data_id"),
                            person,
                            resultSet.getString("user_login"),
                            resultSet.getString("user_password"),
                            state,
                            resultSet.getObject("date_reg").toString()
                    );
                    userDataList.add(userData);
                }
                return new Result<>(userDataList, true, "Success");
            } catch (ClassNotFoundException e) {
                return new Result<>(null, false, e.getMessage());
            } catch (SQLException e) {
                retry++;
                if (retry > 5) return new Result<>(null, false, e.getMessage());
            } finally {
                connectionManager.closeConnection(connection);
            }
        }
    }

    @Override
    public Result<String> insert(UserData user, InsertType insertType) {
        int retry = 0;
        Connection connection = null;
        while (true) {
            try {
                connection = connectionManager.getConnection();
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
            } finally {
                connectionManager.closeConnection(connection);
            }
        }
    }

    @Override
    public Result<UserData> getById(int id) {
        int retry = 0;
        Connection connection = null;
        while (true) {
            try {
                connection = connectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT ud.id AS user_data_id, person_id, user_login, user_password, " +
                                "state_id, date_reg, first_name, last_name, birthday, sex_id, sex_id, s.enabled, s.role, s.state " +
                                "FROM user_data ud JOIN person p ON ud.person_id = p.id JOIN state s ON ud.state_id = s.id WHERE ud.id = ?"
                );
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    State state = new State(
                            resultSet.getInt("state_id"),
                            resultSet.getString("state"),
                            resultSet.getBoolean("enabled"),
                            resultSet.getString("role"));

                    Person person = new Person(
                            resultSet.getInt("person_id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getObject("birthday").toString(),
                            resultSet.getInt("sex_id"));

                    UserData userData = new UserData(
                            resultSet.getInt("user_data_id"),
                            person,
                            resultSet.getString("user_login"),
                            resultSet.getString("user_password"),
                            state,
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
            } finally {
                connectionManager.closeConnection(connection);
            }
        }
    }

    @Override
    public Result<UserData> getByLogin(String login) {
        int retry = 0;
        Connection connection = null;
        while (true) {
            try {
                connection = connectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT ud.id AS user_data_id, person_id, user_login, user_password, " +
                                "state_id, date_reg, first_name, last_name, birthday, sex_id, sex_id, s.enabled, s.role, s.state " +
                                "FROM user_data ud JOIN person p ON ud.person_id = p.id JOIN state s ON ud.state_id = s.id WHERE user_login = ?"
                );
                preparedStatement.setString(1, login);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    State state = new State(
                            resultSet.getInt("state_id"),
                            resultSet.getString("state"),
                            resultSet.getBoolean("enabled"),
                            resultSet.getString("role"));

                    Person person = new Person(
                            resultSet.getInt("person_id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getObject("birthday").toString(),
                            resultSet.getInt("sex_id"));

                    UserData userData = new UserData(
                            resultSet.getInt("user_data_id"),
                            person,
                            resultSet.getString("user_login"),
                            resultSet.getString("user_password"),
                            state,
                            resultSet.getObject("date_reg").toString()
                    );
                    return new Result<>(userData, true, "Success");
                } else {
                    logger.warn(new Log("No such user", login) );
                    return new Result<>(null, false, String.format("User with login = %s not found", login));
                }
            } catch (ClassNotFoundException e) {
                logger.error(new Log("Database driver lost", e));
                return new Result<>(null, false, e.getMessage());
            } catch (SQLException e) {
                retry++;
                if (retry > 5) return new Result<>(null, false, e.getMessage());
                logger.error(new Log("SQL query executing error", e));
            } finally {
                connectionManager.closeConnection(connection);
            }
        }
    }

}
