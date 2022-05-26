package edu.finalproject.webservice.service;

import edu.finalproject.webservice.model.connector.MySQLConnector;
import edu.finalproject.webservice.model.dao.User;
import edu.finalproject.webservice.model.manager.UserManager;
import edu.finalproject.webservice.model.manager.impl.UserManagerImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private final UserManager userManager;

    public UserService(UserManager userManager) {
        this.userManager = userManager;
    }

    public boolean validateUser(String username, String password) {
        try (Connection con = userManager.getConnector().getMySQLConnection()) {

            return userManager.findUser(con, username, password);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }

    }

    public User findById(Integer id) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return userManager.findById(con, id);
        }
    }

    public List<User> findAll() throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return userManager.findAll(con);
        }
    }

    public boolean findByUsernameAndPassword(String username, String password) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return userManager.findUser(con, username, password);
        }
    }

    public int registerUser(User user) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return userManager.registerUser(con, user);
        }
    }

    public boolean updateUser(User user) throws SQLException, ClassNotFoundException {
        try (Connection con = new MySQLConnector().getMySQLConnection()) {
            return userManager.updateUser(con, user);
        }
    }
}
