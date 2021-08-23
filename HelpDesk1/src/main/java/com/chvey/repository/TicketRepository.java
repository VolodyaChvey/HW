package com.chvey.repository;

import com.chvey.domain.Ticket;
import com.chvey.domain.enums.State;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class TicketRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public Ticket findTicketById(Long id) {
        return (Ticket) sessionFactory.getCurrentSession()
                .createQuery("from Ticket where id=:id")
                .setParameter("id", id)
                .getSingleResult();
    }

    public void updateTicket(Ticket ticket) {
        sessionFactory.getCurrentSession()
                .update(ticket);
    }

    public List<Ticket> findTicketsByUserId(int userId) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Ticket where owner_id=:ownerId " +
                        "ORDER BY urgency_id, desired_resolution_date")
                .setParameter("ownerId", userId)
                .getResultList();
    }

    public List<Ticket> findTicketsByStateNew() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Ticket where state_id = 1")
                .getResultList();
    }

    public List<Ticket> findTicketsByApproverId(int userId) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Ticket where approver_id=:approverId and state_id in(:Approved,:Declined,:Cancelled,:in_Progress,:Done)")
                .setParameter("approverId", userId)
                .setParameter("Approved", State.APPROVED.ordinal())
                .setParameter("Declined", State.DECLINED.ordinal())
                .setParameter("Cancelled", State.DECLINED.ordinal())
                .setParameter("in_Progress", State.IN_PROGRESS.ordinal())
                .setParameter("Done", State.DONE.ordinal())
                .getResultList();
    }

    public List<Ticket> findTicketsByStateApproved() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Ticket where state_id=:Approved")
                .setParameter("Approved", State.APPROVED.ordinal())
                .getResultList();
    }

    public List<Ticket> findTicketsByAssigneeId(int userId) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Ticket where assignee_id=:assigneeId and state_id in(:in_Progress,:Done) " +
                        "or (state_id=:Approved)")
                .setParameter("assigneeId", userId)
                .setParameter("in_Progress", State.IN_PROGRESS.ordinal())
                .setParameter("Done", State.DONE.ordinal())
                .setParameter("Approved",State.APPROVED.ordinal())
                .getResultList();
    }

    public Long saveTicket(Ticket ticket) {
        return (Long) sessionFactory.getCurrentSession()
                .save(ticket);
    }

    public List<Ticket> findMyTicketsManager(int userId) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Ticket where owner_id=:ownerId " +
                        "or (approver_id=:approverId and state_id=:Approved)" +
                        "ORDER BY urgency_id, desired_resolution_date")
                .setParameter("ownerId", userId)
                .setParameter("approverId", userId)
                .setParameter("Approved", State.APPROVED.ordinal())
                .getResultList();
    }

    public List<Ticket> findAllTicketsManager(int userId) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Ticket where owner_id=:userId " +
                        "or (state_id =:New) " +
                        "or (approver_id=:userId and state_id in(:Approved,:Declined,:Cancelled,:in_Progress,:Done)) " +
                        "ORDER BY urgency_id, desired_resolution_date")
                .setParameter("userId", userId)
                .setParameter("New", State.NEW.ordinal())
                .setParameter("Approved", State.APPROVED.ordinal())
                .setParameter("Declined", State.DECLINED.ordinal())
                .setParameter("Cancelled", State.DECLINED.ordinal())
                .setParameter("in_Progress", State.IN_PROGRESS.ordinal())
                .setParameter("Done", State.DONE.ordinal())
                .getResultList();
    }

}
