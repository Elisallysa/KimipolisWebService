package edu.finalproject.webservice.api;

import edu.finalproject.webservice.model.dao.User;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

public class UserClient {

    private final WebTarget webTarget;

    public UserClient() {
        Client client = ClientBuilder.newClient();
        this.webTarget = client.target("http://localhost:8080/KimiPolisWebService/api/");
    }

    public Boolean getLogin(User user) {

        return webTarget.path("user/get/"+user.getUsername()+"/"+user.getPassword())
                .request(MediaType.APPLICATION_JSON)
                .get(Boolean.class);
    }

    public String ping() {
        return webTarget.path("user/ping")
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);
    }

    public User postUser (User user) {
        return webTarget.path("user/post")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(user,MediaType.APPLICATION_JSON), User.class);
    }
}
