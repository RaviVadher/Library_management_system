package com.example.demo.service;

import com.example.demo.entities.Member;
import com.example.demo.repos.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getAll() {
        return memberRepository.findAll();
    }

    public Member addMember(Member member) {
        memberRepository.save(member);
        return member;
    }
}
