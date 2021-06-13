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

    public User save(User user) {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO User (id,name) VALUES (?,?)")) {
            ps.setInt(1, user.getId());
            ps.setString(2, user.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
