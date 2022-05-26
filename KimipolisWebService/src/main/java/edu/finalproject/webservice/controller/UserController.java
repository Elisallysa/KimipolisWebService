package edu.finalproject.webservice.controller;

import edu.finalproject.webservice.model.dao.User;
import edu.finalproject.webservice.model.manager.impl.UserManagerImpl;
import edu.finalproject.webservice.service.UserService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

@Path("/user")
public class UserController {

    private final UserService userService;

    public UserController() {
        this.userService = new UserService(new UserManagerImpl());
    }

    @GET
    @Path("/ping")
    public Response ping() {
        return Response.ok().entity("Service online").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() throws SQLException, ClassNotFoundException {
        return Response.ok().entity(userService.findAll()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        try {
            if (id == null) {
                return Response.status(400).entity("Incorrect Parameters").build();
            } else {
                return Response.ok().entity(userService.findById(id)).build();
            }
        } catch (SQLException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        } catch (ClassNotFoundException cnfe) {
            return Response.status(500).entity("Class not found.").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{username}/{password}")
    public Boolean loginUser(@PathParam("username") String username, @PathParam("password") String password) {
        try {
            return userService.findByUsernameAndPassword(username, password);
        } catch (SQLException | ClassNotFoundException e) {
            Response.status(500).entity("Internal Error During DB Interaction").build();
            return false;
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(User user) {
        try {
            int createdId = userService.registerUser(user);
            if (createdId > 0) {
                return Response.status(201).entity(userService.findById(createdId)).build();
            } else {
                return Response.status(500).entity("Internal Error During Creating The City").build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(User user) {
        try {
            User userToDelete = userService.findById(user.getId());
            if (userToDelete != null) {
                if (userService.updateUser(user)) {
                    return Response.status(200).entity(userService.findById(user.getId())).build();
                } else {
                    return Response.status(500).entity("Internal Error During City Update").build();
                }
            } else {
                return Response.status(404).entity("City Not Found").build();
            }
        } catch (SQLException | ClassNotFoundException e) {
            return Response.status(500).entity("Internal Error During DB Interaction").build();
        }
    }
}
