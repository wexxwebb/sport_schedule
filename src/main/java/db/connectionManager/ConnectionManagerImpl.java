package db.connectionManager;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//@Component
public class ConnectionManagerImpl implements ConnectionManager {

    private ConnectionManagerImpl() {
    }

    private static ConnectionManager instance = null;

    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManagerImpl();
        }
        return instance;
    }

    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/sport_schedule",
                "postgres",
                "admin"
        );
        return connection;
    }
}
