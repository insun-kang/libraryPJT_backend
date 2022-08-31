package com.project.library.repository;

import com.project.library.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {
    private final EntityManager em;

    public void save(Comment comment){
        if(comment == null){
            em.persist(comment);
        }
        else{
            em.merge(comment);
        }
    }

    public Comment findOne(Long commentId){
        return em.find(Comment.class, commentId);
    }

    public List<Comment> findByBookId(Long bookId){
       return em.createQuery("select c from Comment c where c.bookId = :bookId", Comment.class)
               .setParameter("bookId", bookId)
               .getResultList();
    }

    public List<Comment> findAll(){
        return em.createQuery("select c from Comment c").getResultList();
    }
}
