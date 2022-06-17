package com.example.db.dao;

import com.example.db.utils.HibernateSessionFactoryUtil;
import jdk.jshell.spi.ExecutionControl;
import org.assertj.core.util.Preconditions;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional
public abstract class AbstractHibernateDAO<T extends Serializable> implements HibernateDAO<T> {

    private Class<T> clazz;

    protected Session getCurrentSession() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession();
    }

    public final void setClazz(final Class<T> clazzToSet) {
        clazz = Preconditions.checkNotNull(clazzToSet);
    }

    @Override
    public T findOne(final Integer id) {
        Session session = getCurrentSession();
        Transaction tx1 = session.beginTransaction();
        T object = session.get(clazz, id);
        tx1.commit();
        session.close();
        return object;
    }

    @Override
    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getName()).list();
    }

    @Override
    public void create(final T entity) {
        Preconditions.checkNotNull(entity);
        Session session = getCurrentSession();
        Transaction tx1 = session.beginTransaction();
        session.saveOrUpdate(entity);
        tx1.commit();
        session.close();
    }

    @Override
    public void save(final T object) {
        Session session = getCurrentSession();
        Transaction tx1 = session.beginTransaction();
        session.save(object);
        tx1.commit();
        session.close();
    }

    @Override
    public T update(final T entity) {
        Preconditions.checkNotNull(entity);
        Session session = getCurrentSession();
        Transaction tx1 = session.beginTransaction();
        session.update(entity);
        tx1.commit();
        session.close();
        return entity;

    }
    @Override
    public void persist(final T entity) {
        Preconditions.checkNotNull(entity);
        Session session = getCurrentSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(entity);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(final T entity) {
        Preconditions.checkNotNull(entity);
        getCurrentSession().delete(entity);
    }

    @Override
    public void deleteById(final Integer entityId) {
        final T entity = findOne(entityId);
        Preconditions.checkNotNull(entity);
        delete(entity);
    }

}
