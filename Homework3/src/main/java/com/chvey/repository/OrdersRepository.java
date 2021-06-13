package com.chvey.repository;

import com.chvey.domain.Order;
import com.chvey.domain.Product;
import com.chvey.sql.SqlHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class OrdersRepository {
    Connection conn = SqlHelper.getConnection();

    public Order save(Order order) {
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO `Order` (id,user_id,total_price) " +
                        "VALUES (?,?,?)")) {
            ps.setLong(1, order.getId());
            ps.setInt(2, order.getUser_id());
            ps.setDouble(3, order.getTotalPrice());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    public void setOrderGood(long orderId, List<String> products) {
        for (String prod : products) {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO Order_Good(order_id,Good_id) " +
                    "VALUES (?,(SELECT id FROM Good WHERE title= ?))")) {
                ps.setLong(1, orderId);
                ps.setString(2, prod);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List getOrderGood(long orderId) {
        List<Product> priceOrder = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement("SELECT title, price FROM order_good " +
                "JOIN good ON order_good.good_id = good.id " +
                "WHERE order_id =?")) {
            ps.setLong(1, orderId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                double price = resultSet.getDouble("price");
                priceOrder.add(new Product(title, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return priceOrder;
    }
}
