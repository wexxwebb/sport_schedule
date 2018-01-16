package pro.kretov.db.DAO.admin;

import pro.kretov.common.PersistType;
import pro.kretov.common.Result;
import pro.kretov.db.POJO.AdminData;
import pro.kretov.db.connectionManager.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static pro.kretov.common.PersistType.RESTORE;

public class AdminDAOImpl implements AdminDAO {
    private ConnectionManager connectionManager;

    public AdminDAOImpl(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Result<List<AdminData>> getAll() {
        int retry = 0;
        while (true) {
            try {
                Connection connection = connectionManager.getConnection();
                Statement statement = connection.createStatement();

                ResultSet result = statement.executeQuery(
                        "SELECT " +
                                "id, " +
                                "user_id " +
                                "FROM admin_data"
                );

                List<AdminData> sexList = new ArrayList<>();
                while (result.next()) {
                    AdminData adminData = new AdminData(
                            result.getInt("id"),
                            result.getInt("user_id")
                    );
                    sexList.add(adminData);
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
    public Result<String> persist(AdminData adminData, PersistType persistType) {
        int retry = 0;
        while (true) {
            try {
                Connection connection = connectionManager.getConnection();
                int[] count;
                if (persistType == RESTORE) {
                    PreparedStatement preparedStatement = connection.prepareStatement(
                            "INSERT INTO admin_data (id, user_id) VALUES (?, ?)"
                    );
                    preparedStatement.setInt(1, adminData.getId());
                    preparedStatement.setInt(2, adminData.getUserId());
                    preparedStatement.addBatch();
                    count = preparedStatement.executeBatch();
                } else {
                    PreparedStatement preparedStatement = connection.prepareStatement(
                            "INSERT INTO admin_data (user_id) VALUES (?)"
                    );
                    preparedStatement.setInt(1, adminData.getUserId());
                    preparedStatement.addBatch();
                    count = preparedStatement.executeBatch();
                }
                return new Result<>(
                        String.format("Inserted %d lines", count[0]),
                        true,
                        "Success"
                );

            } catch (ClassNotFoundException e) {
                return new Result<>(null, false, e.getMessage());
            } catch (SQLException e) {
                retry++;
                if (retry > 5) return new Result<>(null, false, e.getMessage());
            }
        }
    }
}
