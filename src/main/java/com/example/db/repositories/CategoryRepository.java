package com.example.db.repositories;

import com.example.db.dao.AbstractHibernateDAO;
import com.example.db.entity.Category;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository extends AbstractHibernateDAO<Category> {
    public CategoryRepository(){
        setClazz(Category.class );
    }
}
