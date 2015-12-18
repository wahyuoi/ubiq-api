package com.share.dao;

import com.share.core.Uploaded;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 * Created by wahyuoi on 12/18/15.
 */
public class UploadDAO extends AbstractDAO<Uploaded> {
    public UploadDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Uploaded get(int id){
        Uploaded x = get(id);
        Hibernate.initialize(x);
        return x;
    }

    public void upsert(Uploaded uploaded){
        persist(uploaded);
    }

}
