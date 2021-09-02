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

    public void save(Attachment attachment){
        sessionFactory.getCurrentSession()
                .save(attachment);
    }
    public void remove(Attachment attachment){
        sessionFactory.getCurrentSession()
                .remove(attachment);
    }
    public List<Attachment> findAttachmentsByTicketId(Long ticketId){
        return sessionFactory.getCurrentSession()
                .createQuery("from Attachment where ticket_id=:ticketId")
                .setParameter("ticketId",ticketId)
                .getResultList();
    }
    public Attachment findAttachmentById(Long id){
        return (Attachment) sessionFactory.getCurrentSession()
                .createQuery("from Attachment where id=:id")
                .setParameter("id",id)
                .getSingleResult();
    }
}
