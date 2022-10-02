package com.studySpringBasic.springBasic.config;

import com.studySpringBasic.springBasic.discount.DiscountPolicy;
import com.studySpringBasic.springBasic.discount.FixDiscountPolicy;
import com.studySpringBasic.springBasic.member.repository.MemberRepository;
import com.studySpringBasic.springBasic.member.repository.MemberServiceImpl;
import com.studySpringBasic.springBasic.member.repository.MemoryMemberRepository;
import com.studySpringBasic.springBasic.member.service.MemberService;
import com.studySpringBasic.springBasic.order.OrderService;
import com.studySpringBasic.springBasic.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigSpring {

    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        // return new RateDiscountPolicy();
        return new FixDiscountPolicy();
    }

}
