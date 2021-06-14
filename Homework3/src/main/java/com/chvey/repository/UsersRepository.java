package com.chvey.repository;

import com.chvey.domain.User;
import com.chvey.sql.SqlHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UsersRepository {
    Connection conn = SqlHelper.getConnection();

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
    }
}
