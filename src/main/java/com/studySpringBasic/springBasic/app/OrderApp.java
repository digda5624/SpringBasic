package com.studySpringBasic.springBasic.app;

import com.studySpringBasic.springBasic.config.AppConfig;
import com.studySpringBasic.springBasic.discount.FixDiscountPolicy;
import com.studySpringBasic.springBasic.entity.Grade;
import com.studySpringBasic.springBasic.entity.Member;
import com.studySpringBasic.springBasic.entity.Order;
import com.studySpringBasic.springBasic.member.repository.MemberServiceImpl;
import com.studySpringBasic.springBasic.member.repository.MemoryMemberRepository;
import com.studySpringBasic.springBasic.member.service.MemberService;
import com.studySpringBasic.springBasic.order.OrderService;

public class OrderApp {

//    static final OrderService orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
//    static final MemberService memberService = new MemberServiceImpl(new MemoryMemberRepository());

    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Member memberA = new Member(1L, "MemberA", Grade.VIP);
        Member memberB = new Member(1L, "MemberB", Grade.BASIC);
        memberService.join(memberA);
        memberService.join(memberB);

        Order orderA = orderService.createOrder(memberA.getId(), "Book", 1000);
        Order orderB = orderService.createOrder(memberB.getId(), "Book", 1000);

    }
}
