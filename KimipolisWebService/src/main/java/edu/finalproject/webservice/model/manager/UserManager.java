package edu.finalproject.webservice.model.manager;

import edu.finalproject.webservice.model.connector.MySQLConnector;
import edu.finalproject.webservice.model.dao.User;

import java.sql.Connection;
import java.util.List;
import java.util.Set;

public interface UserManager {

    public boolean findUser(Connection con, String name, String pass);

    public int registerUser(Connection con, User user);

    public MySQLConnector getConnector();

    List<User> findAll(Connection con);

    List<User> findAllByIds(Connection con, Set<String> ids);

    User findById(Connection con, Integer id);

    boolean updateUser(Connection con, User user);
}