package com.project.library.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
public class Comment {
    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(columnDefinition = "TEXT")
    private String comment;

    private int rating;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private List<BigComment> bigComments = new ArrayList<>();
    //== 연관관계 메서드 ==//
    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getComments().remove(this);
        }
        this.member = member;
        member.getComments().add(this);
    }
    public void setBook(Book book) {
        if (this.book != null) {
            this.book.getComments().remove(this);
        }
        this.book = book;
        book.getComments().add(this);
    }
    public void resistcreatedAt(LocalDateTime createdAt){
        this.createdAt = createdAt;
    }
    public void resistupdatedAt(LocalDateTime updatedAt){
        this.updatedAt = updatedAt;
    }
}
