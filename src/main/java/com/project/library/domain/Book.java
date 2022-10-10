package com.project.library.domain;

import com.project.library.repository.exception.NotEnoughStockException;
import com.project.library.repository.exception.OverStockException;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Book {
    @Id @GeneratedValue
    @Column(name = "book_id")
    private Long id;

    private String publisher;

    private String link;

    private String title;

    private String author;

    @Column(columnDefinition = "TEXT")
    private String content;

    private double rating;

    private int stockQuantity;

    private int nowStockQuantity;


    private int point;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Wish> wishes = new ArrayList<>();
    //비즈니스 로직
    /**
     * 대여시 도서 감소
     */
    public void decreaseStock(){
        int tmpStock = this.nowStockQuantity--;
        if(tmpStock <0){
            throw new NotEnoughStockException("need more stock");
        }
        this.nowStockQuantity = tmpStock;
    }

    /**
     * 반납시 도서 증가
     */
    public void increaseStock(){
        int tmpStock = this.nowStockQuantity++;
        if(tmpStock> this.stockQuantity){
            throw new OverStockException("over stock");
        }
        this.nowStockQuantity = tmpStock;
    }
}
