package com.chvey.repository;

import com.chvey.domain.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public User findUserByEmail(String email) {
        return (User) sessionFactory.getCurrentSession()
                .createQuery("from User where email=:email")
                .setParameter("email", email)
                .getSingleResult();
    }

}
