package com.calvin.Repository;

import com.calvin.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    private final static String Table = "gc_user";
    private Dao dao;

    public UserRepository() throws SQLException {
        dao = new Dao();
    }

    public boolean login(String username, String password) throws SQLException {
        String query = String.format("SELECT * FROM %s WHERE username = ? AND u_password = ?", Table);
        PreparedStatement preparedStatement = dao.getConnection().prepareStatement(query);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

    public User getUserByUsername(String username) throws SQLException {
        User user;
        String query = String.format("SELECT * FROM %s WHERE username = ?", Table);
        PreparedStatement preparedStatement = dao.getConnection().prepareStatement(query);
        preparedStatement.setString(1, username);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();

        user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));

        return user;
    }

    public int create(String fullname, String email, String username, String password) throws SQLException {
        String query = String.format("INSERT INTO %s(fullname, email, username, u_password) VALUES(?, ?, ?, ?)", Table);
        PreparedStatement preparedStatement = dao.getConnection().prepareStatement(query);
        preparedStatement.setString(1, fullname);
        preparedStatement.setString(2, email);
        preparedStatement.setString(3, username);
        preparedStatement.setString(4, password);

        return preparedStatement.executeUpdate();
    }

    public int update(String fullName, String email, String username, String password, int userId) throws SQLException {
        String query = String.format("UPDATE %s set fullname = ?, email = ?, username = ?, u_password = ? WHERE id = ?", Table);
        PreparedStatement preparedStatement = dao.getConnection().prepareStatement(query);
        preparedStatement.setString(1, fullName);
        preparedStatement.setString(2, email);
        preparedStatement.setString(3, username);
        preparedStatement.setString(4, password);
        preparedStatement.setInt(5, userId);

        return preparedStatement.executeUpdate();
    }
}
