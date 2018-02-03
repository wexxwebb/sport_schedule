package db.dao.jdbc;

import common.*;
import db.dao.TrainingDAO;
import db.entities.Training;
import db.connectionManager.ConnectionManager;
import db.entities.Impl.TrainingImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static common.InsertType.NEW;
import static common.InsertType.RESTORE;

@Component
public class TrainingDAOImpl implements TrainingDAO {

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

    public TrainingDAOImpl() {
    }

    private String chooseTime(long userId, TimePeriod timePeriod) {
        switch (timePeriod) {
            case ALL: return " WHERE user_id = " + userId;
            case FUTURE: return " WHERE training_date > current_date AND user_id = " + userId;
            case TODAY: return " WHERE training_date = current_date AND user_id = " + userId;
            case PAST: return " WHERE training_date < current_date AND user_id = " + userId;
        }
        return "";
    }

    @Override
    public Result<List<Training>> getAll(long userId, TimePeriod timePeriod) {
        int retry = 0;
        Connection connection = null;
        while (true) {
            try {
                connection = connectionManager.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(
                        "SELECT id," +
                                "user_id, " +
                                "create_date, " +
                                "training_date " +
                                "FROM training tr " + chooseTime(userId, timePeriod)
                );

                List<Training> trainingList = new ArrayList<>();
                while (resultSet.next()) {
                    Training training = new TrainingImpl(
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
            } finally {
                connectionManager.closeConnection(connection);
            }
        }
    }

    @Override
    public Result<Training> getByid(int id) {
        int retry = 0;
        Connection connection = null;
        while (true) {
            try {
                connection = connectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT id," +
                                "user_id, " +
                                "create_date, " +
                                "training_date " +
                                "FROM training tr WHERE tr.id = ?"
                );
                preparedStatement.setInt(1, id);

                preparedStatement.addBatch();
                ResultSet resultSet = preparedStatement.executeQuery();

                Training training;
                if (resultSet.next()) {
                    training = new TrainingImpl(
                            resultSet.getInt("id"),
                            resultSet.getInt("user_id"),
                            resultSet.getObject("create_date").toString(),
                            resultSet.getObject("training_date").toString()
                    );
                    return new Result<>(training, true, "Success");
                } else {
                    return new Result<>(null, false, "Нет данных для отображения");
                }
            } catch (ClassNotFoundException e) {
                logger.error(e);
                return new Result<>(null, false, e.getMessage());
            } catch (SQLException e) {
                retry++;
                if (retry > 5) return new Result<>(null, false, e.getMessage());
                logger.error(new Log(e, "retry = " + retry));
            } finally {
                connectionManager.closeConnection(connection);
            }
        }
    }

    @Override
    public Result<Training> insert(Training training, InsertType insertType) {
        int retry = 0;
        while (true) {
            Connection connection = null;
            try {
                connection = connectionManager.getConnection();
                PreparedStatement preparedStatement = null;
                if (insertType == NEW) {
                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO training (user_id, training_date) " +
                                    "VALUES (?, to_date(?, 'YYYY-MM-DD')) RETURNING id, create_date"
                    );
                    preparedStatement.setLong(1, training.getUserId());
                    preparedStatement.setString(2, training.getTrainingDate());

                } else if (insertType == RESTORE) {
                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO training (id, user_id, create_date, training_date) " +
                                    "VALUES (?, ?, to_date(?, 'YYYY-MM-DD'), to_date(?, 'YYYY-MM-DD'))"
                    );
                    preparedStatement.setLong(1, training.getId());
                    preparedStatement.setLong(2, training.getUserId());
                    preparedStatement.setString(3, training.getCreateDate());
                    preparedStatement.setString(4, training.getTrainingDate());

                }

                preparedStatement.addBatch();

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    training.setId(resultSet.getInt("id"));
                    training.setCreateDate(resultSet.getString("create_date"));
                }

                return new Result<>(training, true, "Success");

            } catch (ClassNotFoundException e) {
                logger.error(new Log(e));
                return new Result<>(null, false, e.getMessage());
            } catch (SQLException e) {
                retry++;
                logger.error(new Log(e, training, "retry = " + retry));
                if (retry > 5) return new Result<>(null, false, e.getMessage());
            } finally {
                connectionManager.closeConnection(connection);
            }
        }
    }

    @Override
    public Result<String> delete(int id) {
        int retry = 0;
        Connection connection = null;
        while (true) {
            try {
                connection = connectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                            "DELETE FROM training WHERE id = ?"
                    );

                preparedStatement.setInt(1, id);
                preparedStatement.addBatch();
                preparedStatement.executeBatch();
                return new Result<>("1", true, "Success");

            } catch (ClassNotFoundException e) {
                logger.error(new Log(e));
                return new Result<>(null, false, e.getMessage());
            } catch (SQLException e) {
                retry++;
                logger.error(new Log(e, id, "retry = " + retry));
                if (retry > 5) return new Result<>(null, false, e.getMessage());
            } finally {
                connectionManager.closeConnection(connection);
            }
        }
    }
}
