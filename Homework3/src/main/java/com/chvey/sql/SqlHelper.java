package com.chvey.sql;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class SqlHelper {
    private static final String DB_PROPS_PATH = "/db/db.properties";
    private static String driverName;
    private static String databaseURL;

    private static void property(){
        Properties props = new Properties();
        InputStream inp = ClassLoader.class.getResourceAsStream(DB_PROPS_PATH);
        if (inp != null) {
            try {
                props.load(inp);
                inp.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        driverName = props.getProperty("db.driver");
        databaseURL = props.getProperty("db.url");
    }

    public static Connection getConnection() {
        property();
        Connection conn = null;
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:mem:test_memDB_CLOSE_DELAY=-1",
                    "sa", "");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public static void initDB() {
        String createTables = "CREATE TABLE IF NOT EXISTS User" +
                "(id INT PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "password VARCHAR(255));" +

                "CREATE TABLE IF NOT EXISTS `Order`" +
                "(id LONG PRIMARY KEY," +
                "user_id INT," +
                "total_price DOUBLE," +
                "FOREIGN KEY (user_id) REFERENCES User (id));" +

                "CREATE TABLE IF NOT EXISTS Good" +
                "(id INT PRIMARY KEY AUTO_INCREMENT," +
                "title VARCHAR(50) NOT NULL," +
                "price DOUBLE NOT NULL);" +

                "CREATE TABLE IF NOT EXISTS Order_Good" +
                "(id LONG PRIMARY KEY AUTO_INCREMENT," +
                "order_id LONG," +
                "good_id INT," +
                "FOREIGN KEY (order_id) REFERENCES `Order`(id)," +
                "FOREIGN KEY (good_id) REFERENCES Good (id));";
        Connection conn = SqlHelper.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(createTables)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        insertProducts();
    }

    private static void insertGood(String title, Double price) {
        Connection conn = SqlHelper.getConnection();
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO Good (title,price) VALUES (?,?)")) {
            ps.setString(1, title);
            ps.setDouble(2, price);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertProducts() {
        insertGood("product1", 3.5);
        insertGood("product2", 12.3);
        insertGood("product3", 7.2);
        insertGood("product4", 4.8);
        insertGood("product5", 10.6);
    }
}
