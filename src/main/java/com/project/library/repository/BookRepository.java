package com.project.library.repository;

import com.project.library.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepository {
    private final EntityManager em;

    public void save(Book book){
        if(book.getId() == null) {
            em.persist(book);
        }
        else{
            em.merge(book);
        }
    }
    public Book findOne(Long id){
        return em.find(Book.class, id);
    }

    public List<Book> findAll(){
        return em.createQuery("select b from Book b", Book.class).getResultList();
    }


}
