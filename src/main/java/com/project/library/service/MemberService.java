package com.project.library.service;

import com.project.library.domain.Member;
import com.project.library.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Long join(Member member){
        validationDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }
    private void validationDuplicateMember(Member member){
        List<Member> findmembers = memberRepository.findByEmail(member.getEmail());
        if(!findmembers.isEmpty()){
            throw new IllegalStateException("이미 가입된 이메일입니다.");
        }
    }

    // 회원조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
