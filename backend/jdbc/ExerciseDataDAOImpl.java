package db.dao.jdbc;

import common.InsertType;
import common.Log;
import common.Logged;
import common.Result;
import db.dao._interfaces.ExerciseDataDAO;
import db.entities.inter.ExerciseData;
import db.connectionManager.ConnectionManager;
import db.entities.Impl.ExerciseDataImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static common.InsertType.NEW;
import static common.InsertType.RESTORE;

@Component
public class ExerciseDataDAOImpl implements ExerciseDataDAO {

    @Logged
    private Logger logger;

    private ConnectionManager connectionManager;

    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    @Autowired
    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public ExerciseDataDAOImpl() {

    }

    @Override
    public Result<List<ExerciseData>> getAll() {
        int retry = 0;
        Connection connection = null;
        while (true) {
            try {
                connection = connectionManager.getConnection();
                Statement statement = connection.createStatement();

                ResultSet result = statement.executeQuery(
                        "SELECT " +
                                "id, " +
                                "name " +
                                "FROM exercise_data"
                );

                List<ExerciseData> exerciseDataList = new ArrayList<>();
                while (result.next()) {
                    ExerciseData exerciseDataData = new ExerciseDataImpl(
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
            } finally {
                connectionManager.closeConnection(connection);
            }
        }
    }

    @Override
    public Result<ExerciseData> insert(ExerciseData exerciseDataData, InsertType insertType) {
        int retry = 0;
        Connection connection = null;
        while (true) {
            try {
                connection = connectionManager.getConnection();
                PreparedStatement ps = null;
                if (insertType == NEW) {
                    ps = connection.prepareStatement(
                            "INSERT INTO exercise_data (name) VALUES (?) RETURNING id"
                    );

                    ps.setString(1, exerciseDataData.getName());

                } else if (insertType == RESTORE) {
                    ps = connection.prepareStatement(
                            "INSERT INTO exercise_data (id, name) VALUES (?, ?) RETURNING id"
                    );
                    ps.setLong(1, exerciseDataData.getId());
                    ps.setString(2, exerciseDataData.getName());
                }
                ps.addBatch();
                ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()) {
                    if (insertType == NEW) exerciseDataData.setId(resultSet.getInt("id"));
                    return new Result<>(exerciseDataData, true, "Success");
                }
                logger.error(new Log("Ошибка при вставке", exerciseDataData));
                return new Result<>(null, false, "Ошибка при вставке");

            } catch (ClassNotFoundException e) {
                logger.error(e);
                return new Result<>(null, false, e.getMessage());
            } catch (SQLException e) {
                retry++;
                if (retry > 5) return new Result<>(null, false, e.getMessage());
                logger.error(new Log(e, exerciseDataData, "retry = " + retry));
            } finally {
                connectionManager.closeConnection(connection);
            }
        }
    }

    public Result<List<ExerciseData>> searchByName(String term) {
        int retry = 0;
        Connection connection = null;
        while (true) {
            try {
                connection = connectionManager.getConnection();
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
                    ExerciseData exerciseDataData = new ExerciseDataImpl(
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
            } finally {
                connectionManager.closeConnection(connection);
            }
        }
    }

    @Override
    public Result<ExerciseData> getById(long id) {
        int retry = 0;
        Connection connection = null;
        while (true) {
            try {
                connection = connectionManager.getConnection();
                PreparedStatement ps = connection.prepareStatement(
                        "SELECT " +
                                "id, " +
                                "name " +
                                "FROM exercise_data WHERE id = ?"
                );

                ps.setLong(1, id);
                ps.addBatch();

                ResultSet result = ps.executeQuery();

                if (result.next()) {
                    ExerciseData exerciseData = new ExerciseDataImpl(
                            result.getInt("id"),
                            result.getString("name")
                    );
                    return new Result<>(exerciseData, true, "Success");
                }
                logger.info(new Log("id = " + id, "Нет данных для отображения"));
                return new Result<>(null, false, "Нет данных для отображения");
            } catch (ClassNotFoundException e) {
                logger.error(e);
                return new Result<>(null, false, e.getMessage());
            } catch (SQLException e) {
                retry++;
                if (retry > 5) return new Result<>(null, false, e.getMessage());
                logger.error(new Log(e, "id =" + id, "retry = " + retry));
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
                PreparedStatement ps = connection.prepareStatement(
                        "DELETE FROM exercise_data " +
                                "WHERE id = ?"
                );

                ps.setInt(1, id);
                ps.addBatch();
                ps.executeBatch();

                logger.info(new Log("id = " + id, "Невозможно удалить"));
                return new Result<>("1", true, "Success");

            } catch (ClassNotFoundException e) {
                logger.error(e);
                return new Result<>(null, false, e.getMessage());
            } catch (SQLException e) {
                retry++;
                if (retry > 5) return new Result<>(null, false, e.getMessage());
                logger.error(new Log(e, "id =" + id, "delete retry = " + retry));
            } finally {
                connectionManager.closeConnection(connection);
            }
        }
    }

    @Override
    public Result<ExerciseData> update(ExerciseData exerciseData) {
        int retry = 0;
        Connection connection = null;
        while (true) {
            try {
                connection = connectionManager.getConnection();
                PreparedStatement ps = connection.prepareStatement(
                        "UPDATE exercise_data SET name = ? WHERE id = ? RETURNING id, name"
                );

                ps.setLong(1, exerciseData.getId());
                ps.setString(2, exerciseData.getName());
                ps.addBatch();

                ResultSet result = ps.executeQuery();

                if (result.next()) {
                    ExerciseData updatedExerciseData = new ExerciseDataImpl(
                            result.getInt("id"),
                            result.getString("name")
                    );
                    return new Result<>(updatedExerciseData, true, "Success");
                }
                logger.info(new Log(exerciseData, "Невозможно обновить"));
                return new Result<>(null, false, "Невозможно обновить");
            } catch (ClassNotFoundException e) {
                logger.error(e);
                return new Result<>(null, false, e.getMessage());
            } catch (SQLException e) {
                retry++;
                if (retry > 5) return new Result<>(null, false, e.getMessage());
                logger.error(new Log(e, exerciseData, "update retry = " + retry));
            } finally {
                connectionManager.closeConnection(connection);
            }
        }
    }
}
