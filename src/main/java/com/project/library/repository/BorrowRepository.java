package com.project.library.repository;

import com.project.library.domain.Book;
import com.project.library.domain.Borrow;
import com.project.library.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class BorrowRepository {
    private final EntityManager em;

    public void save(Borrow borrow){
        em.persist(borrow);
    }

    public Borrow findOne(Long id){
        return em.find(Borrow.class, id);
    }

    public Borrow findOneByBorrow(Long memberId, Long bookId){
        return (Borrow) em.createQuery("select b from Member b where b.memberId = :memberId and b.bookId = :bookId", Borrow.class)
                .setParameter("memberId", memberId)
                .setParameter("bookId", bookId)
                .getResultList();
    }


}
