package com.example.db.repositories;

import com.example.db.dao.AbstractHibernateDAO;
import com.example.db.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository  extends AbstractHibernateDAO<User> {
    public UserRepository(){
        setClazz(User.class );
    }
}
