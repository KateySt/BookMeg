package com.example.db.dao;

import com.example.db.utils.HibernateSessionFactoryUtil;
import org.assertj.core.util.Preconditions;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Transactional
public abstract class AbstractHibernateDAO<T extends Serializable> {

    private Class<T> clazz;

    protected Session getCurrentSession() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession();
    }

    public final void setClazz(final Class<T> clazzToSet) {
        clazz = Preconditions.checkNotNull(clazzToSet);
    }

    public T findOne(final Integer id) {
        return (T) getCurrentSession().get(clazz, id);
    }

    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getName()).list();
    }

    public void create(final T entity) {
        Preconditions.checkNotNull(entity);
        getCurrentSession().saveOrUpdate(entity);
    }

    public void save(final T object) {
        Session session = getCurrentSession();
        Transaction tx1 = session.beginTransaction();
        session.save(object);
        tx1.commit();
        session.close();
    }

    public T update(final T entity) {
        Preconditions.checkNotNull(entity);
        return (T) getCurrentSession().merge(entity);
    }

    public void delete(final T entity) {
        Preconditions.checkNotNull(entity);
        getCurrentSession().delete(entity);
    }

    public void deleteById(final Integer entityId) {
        final T entity = findOne(entityId);
        Preconditions.checkNotNull(entity);
        delete(entity);
    }


    public Optional<T> findByName(String name) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from User u where u.nameUser= :user_name");
        query.setParameter("user_name", name);
        return query.uniqueResultOptional();
    }

    public Optional<T> findByEmail(String email) {
        Session session = getCurrentSession();
        //Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("from User u where u.userEmail= :user_email");
        query.setParameter("user_email", email);
        return query.uniqueResultOptional();
    }

    public List<T> findByTitle(String title) {
        Session session = getCurrentSession();
        Query query = session.createQuery("from Book b where b.bookName= :book_name");
        query.setParameter("book_name", title);
        return query.list();
    }

}
