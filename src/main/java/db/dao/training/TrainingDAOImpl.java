package db.dao.training;

import common.InsertType;
import common.Log;
import common.Result;
import common.TimePeriod;
import db.connectionManager.ConnectionManagerImpl;
import db.pojo.Training;
import db.connectionManager.ConnectionManager;
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

    private static Logger logger = Logger.getLogger(TrainingDAOImpl.class);

    //@Autowired
    private ConnectionManager connectionManager = ConnectionManagerImpl.getInstance();

    public TrainingDAOImpl() {
    }

    private String chooseTime(TimePeriod timePeriod) {
        switch (timePeriod) {
            case ALL: return "";
            case FUTURE: return " WHERE training_date > current_date";
            case TODAY: return " WHERE training_date = current_date";
            case PAST: return " WHERE training_date < current_date";
        }
        return "";
    }

    @Override
    public Result<List<Training>> getAll(TimePeriod timePeriod) {
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
                                "FROM training tr " + chooseTime(timePeriod)
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
    public Result<Training> getByid(int id) {
        int retry = 0;
        while (true) {
            try {
                Connection connection = connectionManager.getConnection();
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
                    training = new Training(
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
            }
        }
    }

    @Override
    public Result<Training> insert(Training training, InsertType insertType) {
        int retry = 0;
        while (true) {
            try {
                Connection connection = connectionManager.getConnection();
                PreparedStatement preparedStatement = null;
                if (insertType == NEW) {
                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO training (user_id, training_date) " +
                                    "VALUES (?, to_date(?, 'YYYY-MM-DD')) RETURNING id, create_date"
                    );
                    preparedStatement.setInt(1, training.getUserId());
                    preparedStatement.setString(2, training.getTrainingDate());

                } else if (insertType == RESTORE) {
                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO training (id, user_id, create_date, training_date) " +
                                    "VALUES (?, ?, to_date(?, 'YYYY-MM-DD'), to_date(?, 'YYYY-MM-DD'))"
                    );
                    preparedStatement.setInt(1, training.getId());
                    preparedStatement.setInt(2, training.getUserId());
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
            }
        }
    }

    @Override
    public Result<String> delete(int id) {
        int retry = 0;
        while (true) {
            try {
                Connection connection = connectionManager.getConnection();
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
            }
        }
    }
}
