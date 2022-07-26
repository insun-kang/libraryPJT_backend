package com.project.library.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Borrow {
    @Id @GeneratedValue
    @Column(name = "borrow_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "borrow", cascade = CascadeType.ALL)
    private List<BorrowBook> borrowBooks = new ArrayList<>();

    private LocalDateTime borrowDate;

    private BorrowStatus status;

    //== 연관관계 메서드 ==//
    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getBorrows().remove(this);
        }
        this.member = member;
        member.getBorrows().add(this);
    }


}
