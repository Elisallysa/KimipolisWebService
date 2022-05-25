package edu.finalproject.webservice.model.manager.impl;

import edu.finalproject.webservice.model.connector.MySQLConnector;
import edu.finalproject.webservice.model.dao.User;
import edu.finalproject.webservice.model.manager.UserManager;

import java.sql.*;
import java.util.*;

public class UserManagerImpl implements UserManager {
    @Override
    public boolean findUser(Connection con, String name, String pass) {
        //prepare SQL statement
        String sql = "select * "
                + "from users "
                + "where username = ? and password = ?";

        // Create general statement
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            //Add Parameters
            stmt.setString(1, name);
            stmt.setString(2, pass);
            // Queries the DB
            ResultSet result = stmt.executeQuery();
            // Set before first registry before going through it.
            result.beforeFirst();

            // Initialize variable
            User user = null;

            return result.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int registerUser(Connection con, User user) {
        //prepare SQL statement
        String sql = "INSERT INTO users (id, username, password, mail) values(?,?,?,?)";

        // Create general statement
        try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            //Add Parameters
            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getUser());
            stmt.setString(3, user.getPass());
            stmt.setString(4, user.getMail());
            // Queries the DB
            int affectedRows = stmt.executeUpdate();

            if(affectedRows<=0){
                return 0;
            }

            ResultSet resultSet = stmt.getGeneratedKeys();
            resultSet.beforeFirst();
            resultSet.next();

            return resultSet.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public MySQLConnector getConnector() { return new MySQLConnector(); }

    @Override
    public List<User> findAll(Connection con) {
            // Create general statement
        try (Statement stmt = con.createStatement()) {
            // Queries the DB
            ResultSet result = stmt.executeQuery("SELECT * FROM users");
            // Set before first registry before going through it.
            result.beforeFirst();

            // Initializes variables
            List<User> users = new ArrayList<>();

            // Run through each result
            while (result.next()) {
                // Initializes a user per result
                users.add(new User(result));
            }

            return users;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<User> findAllByIds(Connection con, Set<String> ids) {
        return null;
    }

    @Override
    public User findById(Connection con, Integer userid) {
        //prepare SQL statement
        String sql = "select * "
                + "from users "
                + "where id = ?";

        // Create general statement
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            //Add Parameters
            stmt.setInt(1, userid);

            ResultSet result = stmt.executeQuery();
            // Set before first registry before going through it.
            result.beforeFirst();

            // Initialize variable
            User user = new User(result);

            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateUser(Connection con, User user) {
        //prepare SQL statement
        String sql = "UPDATE users SET username=?, password=?, mail=? WHERE id = ?";

        // Create general statement
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            //Add Parameters
            stmt.setString(1, user.getUser());
            stmt.setString(2, user.getPass());
            stmt.setString(3, user.getMail());
            // Queries the DB
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}