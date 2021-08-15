package com.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The class is JDBC Utils.
 *
 * @author TranAnhTuan
 * @create_date August 13,2021
 */
public class JDBCUtils {
    private Connection connection;
    private static Properties properties;

    /**
     * Constructor for class JdbcUtils.
     *
     * @author TranAnhTuan
     * @create_date August 13, 2021
     */
    public JDBCUtils() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream("src/main/java/resource/database.properties"));
        properties.load(new FileInputStream("src/main/java/resource/message.properties"));
    }

    /**
     * This method is used for connecting to database.
     *
     * @author TranAnhTuan
     * @create_date August 13, 2021
     */
    public void isConnectForTesting() throws SQLException {
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        // Get a Database Connection
        connection = DriverManager.getConnection(url, username, password);
        System.out.println(JDBCUtils.getMessage().getProperty("connect.success"));
    }

    /**
     * This method is used for connecting to database.
     *
     * @author TranAnhTuan
     * @create_date August 13, 2021
     */
    public Connection connect() throws SQLException {
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        //Get a Database Connection
        connection = DriverManager.getConnection(url, username, password);
        return connection;
    }

    public static Properties getMessage(){
        return properties;
    }

    /**
     * This method is used for closing database.
     *
     * @author TranAnhTuan
     * @create_date August 13,2021
     */
    public void disconnect() throws SQLException {
        connection.close();
    }
}
