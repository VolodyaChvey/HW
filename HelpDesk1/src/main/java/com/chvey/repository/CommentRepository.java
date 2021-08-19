package com.chvey.repository;

import com.chvey.domain.Comment;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CommentRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Comment> findCommentsByTicketId(Long id){
        return sessionFactory.getCurrentSession()
                .createQuery("from Comment where ticket_id=:id")
                .setParameter("id",id)
                .getResultList();
    }
    public Long saveComment(Comment comment){
        return (Long)sessionFactory.getCurrentSession()
                .save(comment);
    }
}
