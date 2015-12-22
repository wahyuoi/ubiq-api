package com.share.resources;

import com.codahale.metrics.annotation.Timed;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.share.core.User;
import com.share.dao.UserDAO;
import io.dropwizard.hibernate.UnitOfWork;
import org.hibernate.SessionFactory;

import javax.ws.rs.*;
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
        if (userDAO.getByDevice(user.getDeviceId()) == null)
            user = userDAO.upsert(user);
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @GET
    @Timed
    @UnitOfWork
    @Path("/download/{deviceId}")
    public Response doDownload(@PathParam("deviceId") String deviceId){
        User user = userDAO.getByDevice(deviceId);
        return Response.ok(user.getUploadedList()).build();
    }
}
