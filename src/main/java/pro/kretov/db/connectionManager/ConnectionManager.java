package pro.kretov.db.connectionManager;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionManager {
    Connection getConnection() throws ClassNotFoundException, SQLException;
}
