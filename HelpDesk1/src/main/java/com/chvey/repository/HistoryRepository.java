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

    public List<History> findHistoriesByTicketId(Long id) {
        try {
            return sessionFactory.getCurrentSession()
                    .createQuery("from History where ticket_id=:id " +
                            "order by date desc")
                    .setParameter("id", id)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Long saveHistory(History history) {
        try {
            return (Long) sessionFactory.getCurrentSession()
                    .save(history);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
