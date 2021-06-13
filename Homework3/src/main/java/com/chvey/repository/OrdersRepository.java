package com.chvey.repository;

import com.chvey.domain.Order;
import com.chvey.domain.Product;
import com.chvey.sql.SqlHelper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class OrdersRepository {
    Connection conn = SqlHelper.getConnection();
    LocalDateTime date;

    public Order save(int user_id, double total_price) {
        date = LocalDateTime.now();
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO `Order` (user_id,total_price,`date`) " +
                        "VALUES (?,?,?)")) {
            ps.setInt(1, user_id);
            ps.setDouble(2, total_price);
            ps.setString(3, String.valueOf(date));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(date);
        return getOrder(user_id, total_price, String.valueOf(date)).get();
    }

    public Optional<Order> getOrder(int user_id, double total_price, String date) {
        try (PreparedStatement ps = conn.prepareStatement("SELECT id FROM `Order` " +
                "WHERE user_id = ? AND total_price = ? AND `date`=?;")) {
            ps.setInt(1, user_id);
            ps.setDouble(2, total_price);
            ps.setString(3,date);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                return Optional.of(new Order(id, user_id, total_price, date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void setOrderGood(int order_id, List<String> products) {
        for (String prod : products) {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO Order_Good(order_id,Good_id) " +
                    "VALUES (?,(SELECT id FROM Good WHERE title= ?))")) {
                ps.setInt(1, order_id);
                ps.setString(2, prod);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List getOrderGood(int orderId) {
        List<Product> priceOrder = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement("select title, price from order_good \n" +
                "join good on order_good.good_id = good.id\n" +
                "where order_id = ? ;")) {
            System.out.println(LocalDate.now());
            ps.setInt(1, orderId);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                Double price = resultSet.getDouble("price");
                priceOrder.add(new Product(title, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return priceOrder;
    }
}
