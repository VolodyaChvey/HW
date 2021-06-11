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
        initDB();
        Connection conn = SqlHelper.getConnection();
       try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM USER");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.printf("%d. %s", id, name + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       /* try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM USER")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.printf("%d. %s", id, name + "\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }*/

    }

    public static void initDB() {
        Connection conn = SqlHelper.getConnection();
        try {
            Statement st= conn.createStatement();
            st.executeUpdate(
                    "DROP TABLE IF EXISTS USER;" +
                            "CREATE TABLE USER" +
                            "(ID INT PRIMARY KEY AUTO_INCREMENT," +
                            "NAME VARCHAR(255) NOT NULL," +
                            "PASSWORD VARCHAR(255));" +
                            "INSERT INTO USER(NAME)" +
                            "VALUES('Vasya');"

            );

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
