package com.share.resources;

import com.codahale.metrics.annotation.Timed;
import com.share.core.Image;
import com.share.core.Uploaded;
import com.share.core.User;
import com.share.dao.UploadDAO;
import com.share.dao.UserDAO;
import com.share.utils.Base64;
import io.dropwizard.hibernate.UnitOfWork;
import org.hibernate.SessionFactory;


import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wahyuoi on 12/17/15.
 */
@Path("upload")
@Produces(MediaType.APPLICATION_JSON)
public class UploadResource {
    private final String imagePath;
    private final UserDAO userDAO;
    private final UploadDAO uploadDAO;
    public UploadResource(SessionFactory sessionFactory, String imagePath) {
        this.imagePath = imagePath;
        userDAO = new UserDAO(sessionFactory);
        uploadDAO = new UploadDAO(sessionFactory);
    }

    @POST
    @UnitOfWork
    @Timed
    public Response doUpload(Image image){
        try {
            String path = Base64.convert(image.getBase64(), image.getExt(), imagePath);
            User uploader = userDAO.getByDevice(image.getDeviceId());
            List<User> downloader = userDAO.getBySecret(uploader.getSecret());
            List<String> devices = new ArrayList<>();
            Uploaded uploaded = new Uploaded();
            uploaded.setPath(path);
            for (User user : downloader){
                if (isValid(user, uploader)){
                    devices.add(user.getDeviceId());
                    user.getUploadedList().add(uploaded);
                    uploaded.setUser(user);
                    userDAO.upsert(user);
                }
            }
            return Response.status(Response.Status.CREATED).build();
            // todo push notif
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isValid(User user, User uploader) {
        // todo check secret && check radius
        return true;
    }

}
