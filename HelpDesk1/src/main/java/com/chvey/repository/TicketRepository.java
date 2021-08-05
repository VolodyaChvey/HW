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
    public List<Ticket> findTicketsByStateNew(){
        return sessionFactory.getCurrentSession()
                .createQuery("from Ticket where state_id = New")
                .getResultList();
    }
    public List<Ticket> findTicketsByApproverId(int userId){
        return sessionFactory.getCurrentSession()
                .createQuery("from Ticket where approver_id=:approverId and state_id in(:Approved,:Declined,:Cancelled,:in Progress,:Done)")
                .setParameter("approverId",userId)
                .setParameter("Approved", State.APPROVED.name())
                .setParameter("Declined", State.DECLINED.name())
                .setParameter("Cancelled", State.DECLINED.name())
                .setParameter("in Progress", State.IN_PROGRESS.name())
                .setParameter("Done",State.DONE.name())
                .getResultList();
    }
}
