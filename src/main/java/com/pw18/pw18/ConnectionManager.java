package com.pw18.pw18;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManager {
    private static BasicDataSource dataSource;

    static {
        dataSource = new BasicDataSource();

        dataSource.setUrl("jdbc:derby://localhost:1527/mainDB");
        dataSource.setUsername("APP");
        dataSource.setPassword("password");
        dataSource.setInitialSize(10); // Numero iniziale di connessioni nel pool
        // Imposta altre opzioni di configurazione qui, se necessario
        dataSource.setDriver(new org.apache.derby.jdbc.EmbeddedDriver());
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}

