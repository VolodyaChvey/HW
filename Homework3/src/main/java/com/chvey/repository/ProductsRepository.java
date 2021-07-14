package com.chvey.repository;

import com.chvey.domain.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ProductsRepository {
    @Autowired
    SessionFactory sessionFactor;

    public List getAll() {
        return sessionFactor.getCurrentSession()
                .createQuery("from Product")
                .getResultList();
    }

    public Product getById(int id) {
        return (Product) sessionFactor.getCurrentSession()
                .createQuery("from Product p where p.id=:id")
                .setParameter("id", id)
                .getSingleResult();
    }

    public Product getProd(String name) {
        return (Product) sessionFactor.getCurrentSession()
                .createQuery("from Product where id=(select id from Product where title=:name)")
                .setParameter("name", name)
                .getSingleResult();
    }
}
