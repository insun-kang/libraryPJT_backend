package com.project.library.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    //생성 메서드//
    public void changeBorrowStatus(BorrowStatus status){
        this.status = status;
    }
    public void resistborrowDate(LocalDateTime borrowDate){
        this.borrowDate = borrowDate;
    }
    public void addBorrowBook(BorrowBook borrowBook){
        borrowBooks.add(borrowBook);
        borrowBook.registBorrow(this);
    }

    /**
     * 도서 대여 생성
     * @param member
     * @param borrowBooks
     * @return
     */
    public static Borrow createBorrow(Member member, BorrowBook... borrowBooks){
        Borrow borrow = new Borrow();
        borrow.setMember(member);
        for (BorrowBook borrowBook : borrowBooks){
            borrow.addBorrowBook(borrowBook);
        }
        borrow.changeBorrowStatus(BorrowStatus.BORROW);
        borrow.resistborrowDate(LocalDateTime.now());
        return borrow;
    }


//    public static Borrow reserveBorrow(Member member, BorrowBook... borrowBooks){
//        Borrow borrow = new Borrow();
//        borrow.setMember(member);
//        for (BorrowBook borrowBook : borrowBooks){
//            borrow.addBorrowBook(borrowBook);
//        }
//        borrow.changeBorrowStatus(BorrowStatus.RESERVE);
//        borrow.resistborrowDate(LocalDateTime.now());
//        return borrow;
//    }
        public void giveBack() {
            this.changeBorrowStatus(BorrowStatus.RETURN);
            for(BorrowBook borrowBook : borrowBooks){
                borrowBook.giveBack();
            }
        }

}
