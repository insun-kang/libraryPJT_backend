package com.project.library.service;

import com.project.library.domain.Book;
import com.project.library.domain.Borrow;
import com.project.library.domain.BorrowBook;
import com.project.library.domain.Member;
import com.project.library.repository.BookRepository;
import com.project.library.repository.BorrowRepository;
import com.project.library.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BorrowService {
    private final BorrowRepository borrowRepository;

    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    //도서 대출
    @Transactional
    public Long borrow(Long memberId, Long bookId){
        Member member = memberRepository.findOne(memberId);
        Book book = bookRepository.findOne(bookId);

        //대출내역 생성
        BorrowBook borrowBook = BorrowBook.createBorrowBook(book);

        //대출 생성
        Borrow borrow = Borrow.createBorrow(member, borrowBook);

        //대출 저장
        borrowRepository.save(borrow);

        return borrow.getId();
    }

    //도서 반납(bookid, memberid를 통해 status가 borrow이면 반납한다.
    @Transactional
    public void giveBackBook(Long memberId, Long bookId){
        Borrow borrow = borrowRepository.findOneByBorrow(memberId, bookId);
        borrow.giveBack();
    }


}
