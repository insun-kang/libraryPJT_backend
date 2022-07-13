package com.project.library.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
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
}
