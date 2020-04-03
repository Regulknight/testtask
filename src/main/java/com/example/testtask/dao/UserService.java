package com.example.testtask.dao;

import com.example.testtask.model.User;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lobachev.nikolay
 */

@Service
public class UserService {
    private UserRequestService userRequestService;
    private final DataSource dataSource;

    @Autowired
    private UserService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void setUserRequestService(UserRequestService userRequestService) {
        this.userRequestService = userRequestService;
    }

    public void create(User user) throws SQLException {
        String query = "INSERT INTO testtask.app_user(first_name, last_name, is_active, username, password) VALUES(?, ?, ?, ?, ?)";
        String rolesQuery = "INSERT INTO testtask.app_user_roles(user_id, roles_id) VALUES(?, ?)";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setBoolean(3, user.isActive());
            statement.setString(4, user.getUsername());
            statement.setString(5, user.getPassword());
            statement.execute();
            statement.close();
        }

        User user1 = findByUsername(user.getUsername());

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(rolesQuery);

            statement.setLong(1, user1.getId());
            statement.setLong(2, 1);
            statement.execute();
            statement.close();
        }
    }

    public User findById(long id) throws SQLException {
        String query = "SELECT * FROM testtask.app_user WHERE id = ?";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, String.valueOf(id));
            ResultSet rs = statement.executeQuery();

            User user = null;
            if (rs.next())
                user = createUserFromResultSet(rs);
            rs.close();
            statement.close();

            if (user != null)
                user.setUserRequests(userRequestService.findAllForUser(user));

            return user;
        }
    }

    public void delete(Long user_id) throws SQLException {
        String query = "DELETE FROM testtask.app_user WHERE id = ?";

        userRequestService.deleteAllUserRequests(user_id);

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, String.valueOf(user_id));
            statement.execute();
            statement.close();
        }
    }

    public void save(User user) throws SQLException {
        String updateAppUser = "UPDATE testtask.app_user " +
                "SET first_name = ?, last_name = ?, is_active = ?, username = ?, password = ? WHERE id = ?";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(updateAppUser);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setBoolean(3, user.isActive());
            statement.setString(4, String.valueOf(user.getUsername()));
            statement.setString(5, String.valueOf(user.getPassword()));
            statement.setString(6, String.valueOf(user.getId()));
            statement.execute();
            statement.close();
        }
    }

    public List<User> findAll() throws SQLException {
        String query = "SELECT * FROM testtask.app_user";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            List<User> result = new ArrayList<>();
            while (rs.next()) {
                User user = createUserFromResultSet(rs);
                user.setUserRequests(userRequestService.findAllForUser(user));
                result.add(user);
            }
            rs.close();
            statement.close();
            return result;
        }
    }

    public User findByUsername(String username) throws SQLException {
        String query = "SELECT * FROM testtask.app_user WHERE username LIKE ?";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();

            User user = null;
            if (rs.next())
                user = createUserFromResultSet(rs);
            rs.close();
            statement.close();

            if (user != null)
                user.setUserRequests(userRequestService.findAllForUser(user));

            return user;
        }
    }

    private User createUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setActive(rs.getBoolean("is_active"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        return user;
    }

}
