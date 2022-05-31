package com.example.db.dao;

import com.example.db.entity.Role;
import com.example.db.entity.User;
import com.example.db.utils.HibernateSessionFactoryUtil;
import org.assertj.core.util.Preconditions;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.awt.print.Book;
import java.io.Serializable;
import java.util.List;

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

    public T create(final T entity) {
        Preconditions.checkNotNull(entity);
        getCurrentSession().saveOrUpdate(entity);
        return entity;
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

    public List<T> findByName(String name) {
        Session session = getCurrentSession();
        Criteria criteria= session.createCriteria(User.class)
                .add(Restrictions.eq("nameUser",  name));
        return criteria.list();
    }

    public List<T> findByTitle(String title) {
        Session session = getCurrentSession();
        Criteria criteria= session.createCriteria(clazz)
                .add(Restrictions.eq("bookName",  title));
        return criteria.list();
    }


   /* @Override
    public Object findById(int id) {
        return  HibernateSessionFactoryUtil.getSessionFactory().openSession().load(Object.class, id);
    }

    @Override
    public List findAll() {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from " + Object.class).getResultList();
    }

    @Override
    public void save(Object object) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(object);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Object object) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(object);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Object object) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(object);
        tx1.commit();
        session.close();
    }

    */
}
