package db.connectionManager;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConnectionManagerImplTest {

    @Test
    public void closeConnection() {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            System.out.println("ХУЙ!");
        }

    }
}