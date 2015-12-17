package com.share.resources;

import com.codahale.metrics.annotation.Timed;
import com.share.core.User;
import com.share.dao.UserDAO;
import io.dropwizard.hibernate.UnitOfWork;
import org.hibernate.SessionFactory;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by wahyuoi on 12/17/15.
 */
@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private final UserDAO userDAO;
    public UserResource(SessionFactory sessionFactory) {
        userDAO = new UserDAO(sessionFactory);
    }

    @POST
    @Timed
    @UnitOfWork
    @Path("/register")
    public Response doRegister(User user){
        return Response.ok(user).build();
    }
}
