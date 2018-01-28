package db.dao.sex;

import common.InsertType;
import common.Log;
import common.Result;
import db.connectionManager.ConnectionManagerImpl;
import db.pojo.Sex;
import db.connectionManager.ConnectionManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static common.InsertType.NEW;
import static common.InsertType.RESTORE;

@Component
public class SexDAOImpl implements SexDAO {

    private Logger logger = Logger.getLogger(SexDAOImpl.class);

    private ConnectionManager connectionManager;

    public SexDAOImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public SexDAOImpl() {
        connectionManager = ConnectionManagerImpl.getInstance();
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
    public Result<String> insert(Sex sex, InsertType insertType) {
        int retry = 0;
        while (true) {
            try {
                Connection connection = connectionManager.getConnection();
                PreparedStatement preparedStatement = null;
                if (insertType == NEW) {
                    preparedStatement = connection.prepareStatement(
                            "INSERT INTO sex (sex) VALUES (?)"
                    );

                    preparedStatement.setString(1, sex.getSex());
                } else if (insertType == RESTORE) {
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
                logger.error(new Log(e, sex));
                return new Result<>(null, false, e.getMessage());
            } catch (SQLException e) {
                retry++;
                if (retry > 5) return new Result<>(null, false, e.getMessage());
                logger.error(new Log(e, sex, "retry = " + retry));
            }
        }
    }
}
