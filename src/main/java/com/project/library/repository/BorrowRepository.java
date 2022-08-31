package com.project.library.repository;

import com.project.library.domain.Borrow;
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


}
