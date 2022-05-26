package edu.finalproject.webservice.model.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;
    private String user;
    private String pass;


    public User(ResultSet result) {
        try {
            this.id = result.getInt("id");
            this.user = result.getString("user");
            this.pass = result.getString("pass");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}