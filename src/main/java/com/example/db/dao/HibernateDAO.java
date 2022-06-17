package com.example.db.dao;

import java.util.List;

public interface HibernateDAO<T>{
    T findOne(final Integer id);
    List<T> findAll();
    void create(final T entity);
    void save(final T object);
    T update(final T entity);
    void persist(final T entity);
    void delete(final T entity);
    void deleteById(final Integer entityId);
}
