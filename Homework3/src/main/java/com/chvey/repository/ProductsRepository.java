package com.chvey.repository;

import com.chvey.domain.Product;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class ProductsRepository {
    @Autowired
    LocalSessionFactoryBean factoryBean;

    public Map getProducts(){
        Map<String, Double> priceList = new HashMap<>();
        Session session = Objects.requireNonNull(factoryBean.getObject()).openSession();
        String hql ="SELECT title,price FROM Good";
        List<List<Object>> pList = session.createQuery(hql)
                .setResultTransformer(Transformers.TO_LIST).list();
        for (List<Object> x: pList) {
            priceList.put((String)x.get(0),(Double)x.get(1));
        }
        return priceList;
    }
    public Product getProd(String name){
        Session session = Objects.requireNonNull(factoryBean.getObject()).openSession();
        String hql = "FROM Good WHERE title= :name";
        Query query = session.createQuery(hql);
        query.setString("name",name);
        return (Product)query;
    }

   /* @Autowired
    private Connection conn;

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
    }*/
}
