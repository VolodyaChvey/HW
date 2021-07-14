package com.chvey.repository;

import com.chvey.domain.Order;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class OrdersRepository {
    @Autowired
    SessionFactory sessionFactor;

    public Long save(Order order) {
        return (Long) sessionFactor.getCurrentSession().save(order);
    }

    public Order getOrderById(Long id) {
        return (Order) sessionFactor.getCurrentSession()
                .createQuery("from Order where id=:id")
                .setParameter("id", id)
                .getSingleResult();
    }
}
