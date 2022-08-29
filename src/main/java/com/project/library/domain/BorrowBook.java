package com.project.library.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BorrowBook {
    @Id @GeneratedValue
    @Column(name = "borrow_book_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borrow_id")
    private Borrow borrow;

    public void registBorrow(Borrow borrow){
        this.borrow = borrow;
    }
    public void registBook(Book book){
        this.book = book;
    }

    public static BorrowBook createBorrowBook(Book book){
        BorrowBook borrowBook = new BorrowBook();
        borrowBook.registBook(book);
        book.decreaseStock();

        return borrowBook;
    }

    public void giveBack(){
        getBook().increaseStock();
    }
}
