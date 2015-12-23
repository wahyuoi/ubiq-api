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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wahyuoi on 12/17/15.
 */
@Path("upload")
@Produces(MediaType.APPLICATION_JSON)
public class UploadResource {
    private final String imagePath;
    private final String imageUrl;
    private final UserDAO userDAO;
    private final UploadDAO uploadDAO;
    public UploadResource(SessionFactory sessionFactory, String imagePath, String imageUrl, String parseAppId, String parseRestApi) {
        this.imagePath = imagePath;
        this.imageUrl = imageUrl;
        userDAO = new UserDAO(sessionFactory);
        uploadDAO = new UploadDAO(sessionFactory);
    }

    @POST
    @UnitOfWork
    @Timed
    public Response doUpload(Image image){
        try {
            String filename = Base64.convert(image.getBase64(), image.getExt(), imagePath);
            User uploader = userDAO.getByDevice(image.getDeviceId());
            if (uploader == null)
            	return Response.status(Response.Status.NOT_FOUND).build();
            List<User> dn = userDAO.getBySecret(uploader.getSecret());
            Set<User> downloader = new HashSet<>(0);
            downloader.addAll(dn);
            List<String> devices = new ArrayList<>();
            for (User user : downloader){
                if (isValid(user, uploader)){
                    devices.add(user.getDeviceId());
                    Uploaded uploaded = new Uploaded();
                    uploaded.setPath(imageUrl + "/" + filename);
                    uploaded.setUser(user);
                    uploadDAO.upsert(uploaded);
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
        if (!user.getSecret().equals(uploader.getSecret()))
            return false;
        // todo check radius
        return true;
    }

}
