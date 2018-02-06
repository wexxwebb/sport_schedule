package db.dao.jdbc;

import common.InsertType;
import common.Logged;
import common.Result;
import db.dao._inter.AdminDAO;
import db.entities._inter.AdminData;
import db.connectionManager.ConnectionManager;
import db.entities.Impl.AdminDataImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static common.InsertType.RESTORE;

public class AdminDAOImpl implements AdminDAO {

    @Logged
    private Logger logger;

    private ConnectionManager connectionManager;

    @Autowired
    public void setConnectionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    public AdminDAOImpl() {

    }

    @Override
    public Result<List<AdminData>> getAll() {
        int retry = 0;
        Connection connection = null;
        while (true) {
            try {
                connection = connectionManager.getConnection();
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(
                        "SELECT id, user_id FROM admin_data"
                );
                List<AdminData> adminDataList = new ArrayList<>();
                while (result.next()) {
                    AdminData adminData = new AdminDataImpl(
                            result.getInt("id"),
                            result.getInt("user_id")
                    );
                    adminDataList.add(adminData);
                }
                return new Result<>(adminDataList, true, "Success");
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
    public Result<String> insert(AdminData adminData, InsertType insertType) {
        int retry = 0;
        Connection connection = null;
        while (true) {
            try {
                connection = connectionManager.getConnection();
                int[] count;
                if (insertType == RESTORE) {
                    PreparedStatement preparedStatement = connection.prepareStatement(
                            "INSERT INTO admin_data (id, user_id) VALUES (?, ?)"
                    );
                    preparedStatement.setLong(1, adminData.getId());
                    preparedStatement.setLong(2, adminData.getUserId());
                    preparedStatement.addBatch();
                    count = preparedStatement.executeBatch();
                } else {
                    PreparedStatement preparedStatement = connection.prepareStatement(
                            "INSERT INTO admin_data (user_id) VALUES (?)"
                    );
                    preparedStatement.setLong(1, adminData.getUserId());
                    preparedStatement.addBatch();
                    count = preparedStatement.executeBatch();
                }
                return new Result<>(String.format("Inserted %d lines", count[0]),
                            true,
                            "Success");

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
}
