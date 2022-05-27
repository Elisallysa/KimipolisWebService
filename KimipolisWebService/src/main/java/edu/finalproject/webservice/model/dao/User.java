package edu.finalproject.webservice.model.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class that represents a DTO user.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    // ATTRIBUTES
    private int id;
    private String username;
    private String password;

    /**
     * CONSTRUCTOR OF THE USER CLASS
     * @param result - Result of a MySQL Query
     */
    public User(ResultSet result) {
        try {
            this.id = result.getInt("id");
            this.username = result.getString("username");
            this.password = result.getString("password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}