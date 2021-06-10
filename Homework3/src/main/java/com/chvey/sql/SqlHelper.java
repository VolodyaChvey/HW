package com.chvey.sql;

import java.sql.*;

public class SqlHelper {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test",
                    "sa", "");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        Connection conn = SqlHelper.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM TEST");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.printf("%d. %s", id, name + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void initDB() {
        Connection conn = SqlHelper.getConnection();
    }
}
