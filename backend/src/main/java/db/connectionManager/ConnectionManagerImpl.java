package db.connectionManager;

import common.Log;
import org.apache.log4j.Logger;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Component
public class ConnectionManagerImpl implements ConnectionManager {

    private static Logger logger = Logger.getLogger(ConnectionManagerImpl.class);

    private static final int PORT = 5432;

    private static final String DB = "sport_schedule";

    private static final String CONNECTION_URL = "jdbc:postgresql://localhost:%d/%s";

    private static final String user = "postgres";

    private static final String pass = "admin";

    private final DataSource dataSource;

    @SuppressWarnings("SpellCheckingInspection")
    public ConnectionManagerImpl() {

        PoolProperties p = new PoolProperties();
        p.setUrl(String.format(CONNECTION_URL, PORT, DB));
        p.setDriverClassName("org.postgresql.Driver");
        p.setUsername(user);
        p.setPassword(pass);
        p.setJmxEnabled(true);
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setTestOnReturn(false);
        p.setValidationInterval(30000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        p.setMaxActive(100);
        p.setInitialSize(10);
        p.setMaxWait(10000);
        p.setRemoveAbandonedTimeout(60);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setMinIdle(10);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        p.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;" +
                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
        dataSource = new DataSource();
        dataSource.setPoolProperties(p);
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            logger.warn(e.getMessage(), e);
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        if (connection == null) return;
        int retry = 0;
        while (true) {
            try {
                connection.close();
                return;
            } catch (SQLException e) {
                logger.error(e);
                retry++;
                if (retry > 5) return;
                logger.error(new Log(e, "Closing connection..." + retry));
            }
        }
    }
}
