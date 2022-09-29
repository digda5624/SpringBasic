package com.studySpringBasic.springBasic.order;

import com.studySpringBasic.springBasic.config.AppConfig;
import com.studySpringBasic.springBasic.discount.FixDiscountPolicy;
import com.studySpringBasic.springBasic.entity.Grade;
import com.studySpringBasic.springBasic.entity.Member;
import com.studySpringBasic.springBasic.entity.Order;
import com.studySpringBasic.springBasic.member.repository.MemberServiceImpl;
import com.studySpringBasic.springBasic.member.repository.MemoryMemberRepository;
import com.studySpringBasic.springBasic.member.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

//    MemberService memberService = new MemberServiceImpl(new MemoryMemberRepository());
//    OrderService orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    void init(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    @DisplayName("[sucess] 주문테스트")
    public void 주문테스트(){
        // given
        Member member = new Member(1L, "MemberA", Grade.VIP);

        // when
        memberService.join(member);
        Member findMember = memberService.findMember(member.getId());
        Order order = orderService.createOrder(findMember.getId(), "itemA", 10000);

        // then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
        Assertions.assertThat(order.calculatePrice()).isEqualTo(9000);
    }
}