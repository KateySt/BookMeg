package com.example.db.repositories;

import com.example.db.dao.AbstractHibernateDAO;
import com.example.db.entity.Role;
import com.example.db.entity.User;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


@Repository
public class UserRepository extends AbstractHibernateDAO<User>  {
    public UserRepository() {
        setClazz(User.class);
    }

    public Optional<User> findByName(String name) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from User u where u.nameUser= :user_name");
        query.setParameter("user_name", name);
        return query.uniqueResultOptional();
    }

    public Optional<User> findByEmail(String email) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from User u where u.userEmail= :user_email");
        query.setParameter("user_email", email);
        return query.uniqueResultOptional();
    }
}
