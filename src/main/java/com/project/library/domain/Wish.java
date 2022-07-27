package com.project.library.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wish {
    @Id @GeneratedValue
    @Column(name = "wish_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    private LocalDateTime wishDate;
    //== 연관관계 메서드 ==//
    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getWishes().remove(this);
        }
        this.member = member;
        member.getWishes().add(this);
    }
    public void setBook(Book book) {
        if (this.book != null) {
            this.book.getWishes().remove(this);
        }
        this.book = book;
        book.getWishes().add(this);
    }


}
