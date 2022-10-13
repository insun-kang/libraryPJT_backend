package com.project.library.service;

import com.project.library.domain.Book;
import com.project.library.domain.Comment;
import com.project.library.repository.BookRepository;
import com.project.library.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void saveBook(Book book){
        bookRepository.save(book);
    }

    @Transactional
    public void removeBook(Long bookId){
        bookRepository.remove(bookId);
    }


    public List<Book> findBooks(){
        return bookRepository.findAll();
    }

    public Book findOne(Long bookId){
        return bookRepository.findOne(bookId);
    }

    // 도서 평점
    public double bookRating(Long bookId){
        double result = 0;
        List<Comment> comments = commentRepository.findByBookId(bookId);
        for(Comment comment : comments){

            int tmpGetRating = comment.getRating();

            if(tmpGetRating == 1){
                result += tmpGetRating;
            }
            else if(tmpGetRating == 2){
                result += tmpGetRating*2;
            }
            else if(tmpGetRating == 3){
                result += tmpGetRating*3;
            }
            else if(tmpGetRating == 4){
                result += tmpGetRating*4;
            }
            else if(tmpGetRating == 5){
                result += tmpGetRating*5;
            }
        }
        return Math.round((result / comments.size()) *10 / 10);
    }
}
