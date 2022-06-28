package com.example.db.repositories;

import com.example.db.dao.AbstractHibernateDAO;
import com.example.db.entity.Book;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository extends AbstractHibernateDAO<Book> {
    public BookRepository(){
        setClazz(Book.class );
    }

    public List<Book> findByTitle(String title) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from Book b where b.bookName= :book_name");
        query.setParameter("book_name", title);
        return query.list();
    }
}
