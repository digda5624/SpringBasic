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

/**
 * @Configuartion 을 통해서 직접 @bean 에 등록한다.
 */
@Configuration
public class ConfigSpring {

    @Bean
    public MemberService memberService(){
        System.out.println("memberService method 호출");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService(){
        System.out.println("orderService method 호출");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository(){
        System.out.println("memberRepository method 호출");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        // return new RateDiscountPolicy();
        System.out.println("discountPolicy method 호출");
        return new FixDiscountPolicy();
    }

}
