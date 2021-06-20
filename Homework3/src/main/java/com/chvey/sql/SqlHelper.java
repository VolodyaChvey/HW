package com.chvey.sql;

import java.sql.*;

public class SqlHelper {

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(Config.getDbDriver());
            conn = DriverManager.getConnection(Config.getDbURL(),
                    Config.getLogin(), Config.getPassword());
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public static void initDB() {
        String createTables = "CREATE TABLE IF NOT EXISTS User" +
                "(id INT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(255) NOT NULL," +
                "password VARCHAR(255));" +

                "CREATE TABLE IF NOT EXISTS `Order`" +
                "(id LONG PRIMARY KEY AUTO_INCREMENT," +
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
