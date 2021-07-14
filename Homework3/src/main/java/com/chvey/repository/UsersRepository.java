package com.chvey.repository;

import com.chvey.domain.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public class UsersRepository {
    @Autowired
    SessionFactory sessionFactory;

    public Optional<User> getUserByName(String userName) {
        return Optional.ofNullable((User) sessionFactory.getCurrentSession()
                .createQuery("from User where id=(select id from User where name=:name)")
                .setParameter("name", userName)
                .getSingleResult());
    }

    public User save(String userName) {
        return (User) sessionFactory.getCurrentSession().save(new User(userName));
    }

    public Long getId(String userName) {
        return (Long) sessionFactory.getCurrentSession()
                .createQuery("select id from User where name=:name")
                .setParameter("name", userName)
                .getResultList().get(0);
    }

    public User getUserById(Long id) {
        return (User) sessionFactory.getCurrentSession()
                .createQuery("from User where id=:id")
                .setParameter("id", id)
                .getSingleResult();
    }
}
