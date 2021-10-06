package com.chvey.repository;

import com.chvey.domain.Category;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CategoryRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public Category findCategoryById(Long id) {
        try {
            return (Category) sessionFactory.getCurrentSession()
                    .createQuery("from Category where id=:id")
                    .setParameter("id", id)
                    .getSingleResult();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
