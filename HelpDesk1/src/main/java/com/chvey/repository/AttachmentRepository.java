package com.chvey.repository;

import com.chvey.domain.Attachment;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class AttachmentRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public Long save(Attachment attachment) {
        try {
            return (Long) sessionFactory.getCurrentSession()
                    .save(attachment);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean remove(Attachment attachment) {
        try {
            sessionFactory.getCurrentSession().remove(attachment);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Attachment> findAttachmentsByTicketId(Long ticketId) {
        try {
            return sessionFactory.getCurrentSession()
                    .createQuery("from Attachment where ticket_id=:ticketId")
                    .setParameter("ticketId", ticketId)
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public Attachment findAttachmentById(Long id) {
        try {
            return (Attachment) sessionFactory.getCurrentSession()
                    .createQuery("from Attachment where id=:id")
                    .setParameter("id", id)
                    .getSingleResult();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
