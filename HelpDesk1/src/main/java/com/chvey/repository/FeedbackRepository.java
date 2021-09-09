package com.chvey.repository;

import com.chvey.domain.Feedback;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class FeedbackRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public Long save(Feedback feedback) {
        return (Long) sessionFactory.getCurrentSession()
                .save(feedback);
    }

    public Feedback findFeedbackByTicketId(Long ticketId) {
        return (Feedback) sessionFactory.getCurrentSession()
                .createQuery("from Feedback where ticket_id=:ticketId")
                .setParameter("ticketId", ticketId)
                .getSingleResult();
    }
}
