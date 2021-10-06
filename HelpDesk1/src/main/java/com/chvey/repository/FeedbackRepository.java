package com.chvey.repository;

import com.chvey.domain.Feedback;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public class FeedbackRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public Optional<Long> save(Feedback feedback) {
        return Optional.ofNullable((Long) sessionFactory.getCurrentSession().save(feedback));
    }

    public Optional<Feedback> findFeedbackByTicketId(Long ticketId) {
       try {
            return Optional.of((Feedback) sessionFactory.getCurrentSession()
                    .createQuery("from Feedback where ticket_id=:ticketId")
                    .setParameter("ticketId", ticketId)
                    .getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
