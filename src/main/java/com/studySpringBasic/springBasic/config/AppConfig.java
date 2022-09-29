package com.studySpringBasic.springBasic.config;

import com.studySpringBasic.springBasic.discount.FixDiscountPolicy;
import com.studySpringBasic.springBasic.member.repository.MemberServiceImpl;
import com.studySpringBasic.springBasic.member.repository.MemoryMemberRepository;
import com.studySpringBasic.springBasic.member.service.MemberService;
import com.studySpringBasic.springBasic.order.OrderService;
import com.studySpringBasic.springBasic.order.OrderServiceImpl;

/**
 * 담당자의 역할
 */
public class AppConfig {

    /**
     * 생성자를 통해서 주입을 해준다.
     */
    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    /**
     * 구성 영역 orderService 에서 Memory Repo 와 FixDiscount 정책을 사용하겠다.
     */
    public OrderService orderService(){
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }

}
