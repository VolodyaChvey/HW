package com.chvey.repository;

import com.chvey.domain.User;
import com.chvey.domain.enums.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List<User> findAllManagers() {
        return sessionFactory.getCurrentSession()
                .createQuery("from User where role_id=:manager")
                .setParameter("manager", Role.MANAGER.name())
                .getResultList();
    }
    public List<User> findAllEngineers(){
        return sessionFactory.getCurrentSession()
                .createQuery("from User where role_id=:engineer")
                .setParameter("engineer",Role.ENGINEER.name())
                .getResultList();
    }
}
