package com.chvey.repository;

import com.chvey.domain.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

@Repository
@Transactional
public class UsersRepository {
    @Autowired
    SessionFactory sessionFactory;

    public Optional<User> getUserByName(String userName) {
        Session session = sessionFactory.getCurrentSession();
       Query query = session.createQuery("FROM User  WHERE name=:name", User.class);
        query.setParameter("name", userName);

        return Optional.ofNullable((User) query);
    }
    public User save(String userName){
        User user = new User(userName);
        Session session = sessionFactory.getCurrentSession();
        return (User)session.save(user);
    }
       /* return Optional.of((User) session.createQuery("FROM User WHERE name=?",User.class));
    }
   /* @Autowired
    private Connection conn ;

    public Optional<User> getUserByName(String userName) {
        try (PreparedStatement ps = conn.prepareStatement("SELECT id FROM User " +
                "WHERE name = ? ;")) {
            ps.setString(1, userName);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                return Optional.of(new User(id, userName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public User save(String userName) {
        User user = new User(userName);
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO User (name) VALUES (?)",PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getName());
            ps.execute();
            ResultSet resultSet = ps.getGeneratedKeys();
            while (resultSet.next()){
                user.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }*/

}
