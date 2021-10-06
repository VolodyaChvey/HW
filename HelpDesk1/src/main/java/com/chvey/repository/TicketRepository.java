package com.chvey.repository;

import com.chvey.domain.Ticket;
import com.chvey.domain.User;
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
        try {
            return (Ticket) sessionFactory.getCurrentSession()
                    .createQuery("from Ticket where id=:id")
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateTicket(Ticket ticket) {
        try {
            sessionFactory.getCurrentSession()
                    .update(ticket);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Ticket> findTicketsByUserId(int userId) {
        try {
            return sessionFactory.getCurrentSession()
                    .createQuery("from Ticket where owner_id=:ownerId " +
                            "ORDER BY urgency_id, desired_resolution_date")
                    .setParameter("ownerId", userId)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

  /*  public List<Ticket> findTicketsByStateNew() {

        return sessionFactory.getCurrentSession()
                .createQuery("from Ticket where state_id = 1")
                .getResultList();
    }*/

  /*  public List<Ticket> findTicketsByApproverId(int userId) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Ticket where approver_id=:approverId and state_id in(:Approved,:Declined,:Cancelled,:in_Progress,:Done)")
                .setParameter("approverId", userId)
                .setParameter("Approved", State.APPROVED.ordinal())
                .setParameter("Declined", State.DECLINED.ordinal())
                .setParameter("Cancelled", State.DECLINED.ordinal())
                .setParameter("in_Progress", State.IN_PROGRESS.ordinal())
                .setParameter("Done", State.DONE.ordinal())
                .getResultList();
    }*/

   /* public List<Ticket> findTicketsByStateApproved() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Ticket where state_id=:Approved")
                .setParameter("Approved", State.APPROVED.ordinal())
                .getResultList();
    }*/

    public List<Ticket> findTicketsByAssigneeId(int userId) {
        try {
            return sessionFactory.getCurrentSession()
                    .createQuery("from Ticket where assignee_id=:assigneeId and state_id in(:in_Progress,:Done) " +
                            "or (state_id=:Approved)")
                    .setParameter("assigneeId", userId)
                    .setParameter("in_Progress", State.IN_PROGRESS.ordinal())
                    .setParameter("Done", State.DONE.ordinal())
                    .setParameter("Approved", State.APPROVED.ordinal())
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Long saveTicket(Ticket ticket) {
        try {
            return (Long) sessionFactory.getCurrentSession()
                    .save(ticket);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

  /*  public List<Ticket> findMyTicketsManager(int userId) {
        try {
            return sessionFactory.getCurrentSession()
                    .createQuery("from Ticket where owner_id=:ownerId " +
                            "or (approver_id=:approverId and state_id=:Approved)" +
                            "ORDER BY urgency_id, desired_resolution_date")
                    .setParameter("ownerId", userId)
                    .setParameter("approverId", userId)
                    .setParameter("Approved", State.APPROVED.ordinal())
                    .getResultList();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }*/

    public List<Ticket> findAllTicketsManager(int userId) {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateStateTicket(Long id, State state) {
        try {
            sessionFactory.getCurrentSession()
                    .createQuery("update Ticket SET state_id=:state where id=:id")
                    .setParameter("state", state.ordinal())
                    .setParameter("id", id)
                    .executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addApproverTicket(Long id, User user) {
        try {
            sessionFactory.getCurrentSession()
                    .createQuery("update Ticket set approver_id=:approverId where id=:id")
                    .setParameter("approverId", user.getId())
                    .setParameter("id", id)
                    .executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addAssigneeticket(Long id, User user) {
        try {
            sessionFactory.getCurrentSession()
                    .createQuery("update Ticket set assignee_id=:assigneeId where id=:id")
                    .setParameter("assigneeId", user.getId())
                    .setParameter("id", id)
                    .executeUpdate();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
