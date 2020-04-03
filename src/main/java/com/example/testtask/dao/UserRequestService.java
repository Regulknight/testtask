package com.example.testtask.dao;

import com.example.testtask.model.User;
import com.example.testtask.model.UserRequest;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lobachev.nikolay
 */

@Service
public class UserRequestService {
    private final DataSource dataSource;
    private UserService userService;

    @Autowired
    private UserRequestService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void create(UserRequest userRequest) throws SQLException {
        String query = "INSERT INTO testtask.user_request(title, description, request_date, app_user_id) VALUES(?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userRequest.getTitle());
            statement.setString(2, userRequest.getDescription());
            statement.setDate(3, (Date) userRequest.getRequestDate());
            statement.setString(4, String.valueOf(userRequest.getUser().getId()));
            statement.execute();
            statement.close();
        }
    }

    public UserRequest findById(long id) throws SQLException {
        String query = "SELECT * FROM testtask.user_request WHERE id = ?";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, String.valueOf(id));
            ResultSet rs = statement.executeQuery();
            UserRequest userRequest = null;
            if (rs.next()) {
                userRequest = createUserRequestFromResultSet(rs);
                userRequest.setUser(userService.findById(userRequest.getId()));
            }
            rs.close();
            statement.close();
            if (userRequest == null) {
                throw new SQLException("User request not found");
            }

            return userRequest;
        }
    }

    public void delete(Long userRequestId) throws SQLException {
        String query = "DELETE FROM testtask.user_request WHERE id = ?";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, String.valueOf(userRequestId));
            statement.execute();
            statement.close();
        }
    }

    public void deleteAllUserRequests(Long userId) throws SQLException {
        String query = "DELETE FROM testtask.user_request WHERE app_user_id = ?";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, String.valueOf(userId));
            statement.execute();
            statement.close();
        }
    }

    public void save(UserRequest userRequest) throws SQLException {
        String query = "UPDATE TESTTASK.USER_REQUEST " +
                "SET TITLE = ?, DESCRIPTION = ?, request_date = ?, APP_USER_ID = ? WHERE ID = ?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userRequest.getTitle());
            statement.setString(2, userRequest.getDescription());
            statement.setDate(3, new Date(userRequest.getRequestDate().getTime()));
            statement.setString(4, String.valueOf(userRequest.getUser().getId()));
            statement.setString(5, String.valueOf(userRequest.getId()));
            statement.execute();
            statement.close();
        }
    }

    public List<UserRequest> findAll() throws SQLException {
        String query = "SELECT * FROM testtask.user_request";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            List<UserRequest> result = new ArrayList<>();
            while (rs.next()) {
                UserRequest userRequest = createUserRequestFromResultSet(rs);
                userRequest.setUser(userService.findById(rs.getLong("app_user_id")));
                result.add(userRequest);
            }
            rs.close();
            statement.close();
            return result;
        }
    }

    public List<UserRequest> findAllForUser(User user) throws SQLException {
        String query = "SELECT * FROM testtask.user_request WHERE app_user_id = ?";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, user.getId());
            ResultSet rs = statement.executeQuery();
            List<UserRequest> result = new ArrayList<>();
            while (rs.next()) {
                UserRequest userRequest = createUserRequestFromResultSet(rs);
                userRequest.setUser(user);
                result.add(userRequest);
            }
            rs.close();
            statement.close();
            return result;
        }
    }

    private UserRequest createUserRequestFromResultSet(ResultSet rs) throws SQLException {
        UserRequest userRequest = new UserRequest();
        userRequest.setId(rs.getLong("id"));
        userRequest.setTitle(rs.getString("title"));
        userRequest.setDescription(rs.getString("description"));
        userRequest.setRequestDate(rs.getDate("request_date"));
        return userRequest;
    }
}
