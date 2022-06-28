package com.example.db.repositories;

import com.example.db.dao.AbstractHibernateDAO;
import com.example.db.entity.Author;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorRepository extends AbstractHibernateDAO<Author> {
    public AuthorRepository() {
        setClazz(Author.class);
    }
}