package com.example.db.repositories;

import com.example.db.dao.AbstractHibernateDAO;
import com.example.db.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepository extends AbstractHibernateDAO<Role> {
    public RoleRepository(){
        setClazz(Role.class);
    }
}
