package db.dao.jdbc;

import common.*;
import db.dao._interfaces.TrainingDAO;
import db.entities.inter.Training;
import db.connectionManager.ConnectionManager;
import db.entities.Impl.TrainingImpl;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    @Nullable
    @Override
    public List<Training> getAll(long userId, TimePeriod timePeriod) {
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
                return trainingList;
            } catch (ClassNotFoundException e) {
                return null;
            } catch (SQLException e) {
                retry++;
                if (retry > 5) return null;
            } finally {
                connectionManager.closeConnection(connection);
            }
        }
    }

    @Nullable
    @Override
    public Training getByid(int id) {
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
                    return training;
                } else {
                    return null;
                }
            } catch (ClassNotFoundException e) {
                logger.error(e);
                return null;
            } catch (SQLException e) {
                retry++;
                if (retry > 5) return null;
                logger.error(new Log(e, retry));
            } finally {
                connectionManager.closeConnection(connection);
            }
        }
    }

    @Nullable
    @Override
    public Training insert(Training training) {
        int retry = 0;
        while (true) {
            Connection connection = null;
            try {
                connection = connectionManager.getConnection();
                PreparedStatement preparedStatement;
                if (training.getId() == 0) {
                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO training (user_id, training_date) " +
                                    "VALUES (?, to_date(?, 'YYYY-MM-DD')) RETURNING id, create_date"
                    );
                    preparedStatement.setLong(1, training.getUserId());
                    preparedStatement.setString(2, training.getTrainingDate());

                } else {
                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO training (id, user_id, create_date, training_date) " +
                                    "VALUES (?, ?, to_date(?, 'YYYY-MM-DD'), to_date(?, 'YYYY-MM-DD')) RETURNING id, create_date"
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

                return training;

            } catch (ClassNotFoundException e) {
                logger.error(new Log(e));
                return null;
            } catch (SQLException e) {
                retry++;
                logger.error(new Log(e, training, retry));
                if (retry > 5) return null;
            } finally {
                connectionManager.closeConnection(connection);
            }
        }
    }

    @Nullable
    @Override
    public boolean delete(int id) {
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
                return true;

            } catch (ClassNotFoundException e) {
                logger.error(e);
                return false;
            } catch (SQLException e) {
                retry++;
                logger.error(new Log(e, id, retry));
                if (retry > 5) return false;
            } finally {
                connectionManager.closeConnection(connection);
            }
        }
    }
}
