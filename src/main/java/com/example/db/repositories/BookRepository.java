package com.example.db.repositories;

import com.example.db.dao.AbstractHibernateDAO;
import com.example.db.entity.Book;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository extends AbstractHibernateDAO<Book> {
    public BookRepository(){
        setClazz(Book.class );
    }

}
