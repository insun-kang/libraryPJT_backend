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


}
