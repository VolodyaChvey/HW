package com.chvey.repository;

import com.chvey.domain.Order;
import com.chvey.domain.Product;
import com.chvey.domain.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class OrdersRepository {
    @Autowired
    LocalSessionFactoryBean factoryBean;
    public  Order save(User user, double totalPrice){
        Order order = new Order(user, totalPrice);
        Session session = Objects.requireNonNull(factoryBean.getObject()).openSession();
        return (Order)session.save(order);
    }
    public void setOrderGood(long orderId, List<String> products){
        Session session = Objects.requireNonNull(factoryBean.getObject()).openSession();
        for (String product:products) {
            String sql = "INSERT INTO Order_Good(order_id,Good_id) " +
                    "VALUES (:orderId,(SELECT id FROM Good WHERE title= :product))";
            Query query = session.createSQLQuery(sql);
            query.setLong("orderId",orderId);
            query.setString("product",product);
            query.executeUpdate();
        }
    }
    public List getOrderGood(long orderId){
        Session session = Objects.requireNonNull(factoryBean.getObject()).openSession();
        String sql ="SELECT title, price FROM order_good " +
                "JOIN good ON order_good.good_id = good.id " +
                "WHERE order_id =:orderId";
        Query query = session.createSQLQuery(sql);
        query.setString("orderId", String.valueOf(orderId));
        return query.getResultList();
    }
   /* @Autowired
    private Connection conn;

    public Order save(int userId, double totalPrice) {
        Order order = new Order(userId, totalPrice);
        String[] idColumn = {"id"};
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO `Order` (user_id,total_price) " +
                        "VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, userId);
            ps.setDouble(2, totalPrice);
            ps.execute();
            ResultSet resultSet = ps.getGeneratedKeys();
            while (resultSet.next()) {
                long orderId = resultSet.getLong(1);
                order.setId(orderId);
            }
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
    }*/
}
