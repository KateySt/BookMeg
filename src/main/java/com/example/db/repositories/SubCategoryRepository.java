package com.example.db.repositories;

import com.example.db.dao.AbstractHibernateDAO;
import com.example.db.entity.SubCategory;
import org.springframework.stereotype.Repository;

@Repository
public class SubCategoryRepository extends AbstractHibernateDAO<SubCategory> {
    public SubCategoryRepository() {
        setClazz(SubCategory.class);
    }
}
