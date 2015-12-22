package com.share.dao;

import com.share.core.User;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by wahyuoi on 12/17/15.
 */
public class UserDAO extends AbstractDAO<User> {
    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public User get(int id){
        User x = get(id);
        Hibernate.initialize(x);
        return x;
    }

    public User upsert(User user){
        return persist(user);
    }

    public User getByDevice(String deviceId) {
        return uniqueResult(criteria().add(Restrictions.eq("deviceId", deviceId)));
    }

    public List<User> getBySecret(String secret) {
        return list(criteria().add(Restrictions.like("secret", secret)));
    }
}
