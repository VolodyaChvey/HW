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

    public List<Ticket> findTicketsByUserId(int userId) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Ticket where owner_id=:ownerId")
                .setParameter("ownerId", userId)
                .getResultList();



    }

    public List<Ticket> findTicketsByStateNew() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Ticket where state_id = New")
                .getResultList();
    }

    public List<Ticket> findTicketsByApproverId(int userId) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Ticket where approver_id=:approverId and state_id in(:Approved,:Declined,:Cancelled,:in_Progress,:Done)")
                .setParameter("approverId", userId)
                .setParameter("Approved", State.APPROVED.name())
                .setParameter("Declined", State.DECLINED.name())
                .setParameter("Cancelled", State.DECLINED.name())
                .setParameter("in_Progress", State.IN_PROGRESS.name())
                .setParameter("Done", State.DONE.name())
                .getResultList();
    }

    public List<Ticket> findTicketsByStateApproved() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Ticket where state_id=:Approved")
                .setParameter("Approved", State.APPROVED.name())
                .getResultList();
    }

    public List<Ticket> findTicketsByAssigneeId(int userId) {
        return sessionFactory.getCurrentSession()
                .createQuery("fromTicket where assignee_id=:assigneeId and state_id in(:in_Progress,:Done")
                .setParameter("assigneeId", userId)
                .setParameter("in_Progress", State.IN_PROGRESS.name())
                .setParameter("Done", State.DONE.name())
                .getResultList();
    }

}
