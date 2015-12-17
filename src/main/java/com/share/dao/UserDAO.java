package com.share.dao;

import com.share.core.User;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

/**
 * Created by wahyuoi on 12/17/15.
 */
public class UserDAO extends AbstractDAO<User> {
    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public User get(int id){
        return get(id);
    }

    public User upsert(User user){
        return persist(user);
    }

}
