package edu.finalproject.webservice.model.dao;

import lombok.Getter;
import lombok.Setter;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
public class User {

    private int id;
    private String user;
    private String mail;
    private String pass;


    public User(ResultSet result) {
        try {
            this.id = result.getInt("id");
            this.user = result.getString("user");
            this.mail = result.getString("mail");
            this.pass = result.getString("pass");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User(int id, String user, String mail, String pass) {
        this.id=id;
        this.user=user;
        this.mail=mail;
        this.pass=pass;
    }
}