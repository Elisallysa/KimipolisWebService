package edu.finalproject.webservice.api;

import edu.finalproject.webservice.api.dto.Notification;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

public class NotificationClient {

    private WebTarget webTarget;

    public NotificationClient() {
        Client client = ClientBuilder.newClient();
        this.webTarget = client.target("http://localhost:8080/KimipolisWebService/api/");
    }

    public Notification getNotification(String id) {

        return webTarget.path("notifications/get/"+id)
                .request(MediaType.APPLICATION_JSON)
                .get(Notification.class);
    }

    public Notification getNotification(String id, String name) {

        return webTarget.path("notifications/get/"+id+"/name")
                .queryParam("name", name)
                .request(MediaType.APPLICATION_JSON)
                .get(Notification.class);
    }

    public Notification putNotification(String id, String name) {

        return webTarget.path("notifications/get/"+id+"/"+name)
                .queryParam("name", name)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity("",MediaType.APPLICATION_JSON), Notification.class);
    }

    public Notification postNotification(Notification notification) {

        return webTarget.path("notifications/post")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(notification,MediaType.APPLICATION_JSON), Notification.class);
    }
}