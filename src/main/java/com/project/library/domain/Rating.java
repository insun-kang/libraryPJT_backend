package com.project.library.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Rating {
    @Id @GeneratedValue
    @Column(name = "rating_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    private int oneCount;

    private int twoCount;

    private int threeCount;

    private int fourCount;

    private int fiveCount;

    //== 연관관계 메서드 ==//
    public void setBook(Book book) {
        if (this.book != null) {
            this.book.getRatings().remove(this);
        }
        this.book = book;
        book.getRatings().add(this);
    }

}
