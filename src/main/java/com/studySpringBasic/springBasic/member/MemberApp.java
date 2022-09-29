package com.studySpringBasic.springBasic.member;

import com.studySpringBasic.springBasic.config.AppConfig;
import com.studySpringBasic.springBasic.entity.Grade;
import com.studySpringBasic.springBasic.entity.Member;
import com.studySpringBasic.springBasic.member.repository.MemberServiceImpl;
import com.studySpringBasic.springBasic.member.repository.MemoryMemberRepository;
import com.studySpringBasic.springBasic.member.service.MemberService;

public class MemberApp {

//    public static final MemberService memberService = new MemberServiceImpl();
//    public static final MemberService memberService = new MemberServiceImpl(new MemoryMemberRepository());
    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        System.out.println("member1 = " + findMember.getName());
        System.out.println("member.getName() = " + member.getName());
    }
}
