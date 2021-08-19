package com.chvey.repository;

import com.chvey.domain.History;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class HistoryRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<History> findHistoriesByTicketId(Long id){
        return sessionFactory.getCurrentSession()
                .createQuery("from History where ticket_id=:id")
                .setParameter("id",id)
                .getResultList();
    }
    public Long saveHistory(History history){
        return (Long)sessionFactory.getCurrentSession()
                .save(history);
    }
}
