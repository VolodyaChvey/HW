package com.chvey.repository;

import com.chvey.domain.Product;
import com.chvey.sql.SqlHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ProductsRepository {
    Connection conn = SqlHelper.getConnection();

    public Map getProducts() {
        Map<String, Double> priceList = new HashMap<>();
        try (PreparedStatement ps = conn.prepareStatement("SELECT title,price FROM Good")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                Double price = resultSet.getDouble("price");
                priceList.put(title, price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return priceList;
    }

    public Product getProd(String name) {
        Product product = null;
        try (PreparedStatement ps = conn.prepareStatement("SELECT price FROM Good " +
                "WHERE title = ?;")) {
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                double price = resultSet.getDouble("price");
                product = new Product(name, price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
}
