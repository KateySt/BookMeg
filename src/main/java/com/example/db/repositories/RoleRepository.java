package com.example.db.repositories;

import com.example.db.dao.AbstractHibernateDAO;
import com.example.db.entity.Role;
import com.example.db.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoleRepository extends AbstractHibernateDAO<Role> {
    public RoleRepository(){
        setClazz(Role.class);
    }

}
