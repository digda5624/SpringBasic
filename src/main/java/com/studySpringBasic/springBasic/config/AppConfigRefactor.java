package com.studySpringBasic.springBasic.config;

import com.studySpringBasic.springBasic.discount.DiscountPolicy;
import com.studySpringBasic.springBasic.discount.FixDiscountPolicy;
import com.studySpringBasic.springBasic.member.repository.MemberRepository;
import com.studySpringBasic.springBasic.member.repository.MemberServiceImpl;
import com.studySpringBasic.springBasic.member.repository.MemoryMemberRepository;
import com.studySpringBasic.springBasic.member.service.MemberService;
import com.studySpringBasic.springBasic.order.OrderService;
import com.studySpringBasic.springBasic.order.OrderServiceImpl;

/**
 * 기존에 역할들을 배분하는 과정에서 뭔가 리팩토링이 필요했다.
 *
 * 각각 역할에 대해서 정확한 배분이 이루어 졌다.
 * 따라서 우리는 사용영역의 코드를 전혀 건드리지 않고 구성 영역의 코드만 변경해서 변경이 가능했다.
 * OCP 와 DIP 를 지키게 되었다.
 *
 * 결국 좋은 코드는 1. 실행 코드와 구성 코드의 분리이다.
 *
 * 내가 구성 요소들을 직접 생성하고 제어를 하게 된다면 (직접 제어의 흐름을 관리하면) - 라이브러리
 * 외부에서 내가 작성한 코드들을 제어하고 대신 실행하면 (외부에서 제어의 흐름을 관리하면) 그것은 프레임워크이다. => 외부에서 제어한다 제어의 역전이 일어남
 *
 * DI 컨테이너 => 객체를 생성하고 관리하면서 의존관계를 연결해주는 시스템
 * IOC 컨테이너 => 제어의 역전을 담당하는 시스템
 */
public class AppConfigRefactor {

    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    private MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    /**
     * 할인 정책이 변경되면 여기서 FixDiscountPolicy 를 Rate 로 변경한다.
     */
    private DiscountPolicy discountPolicy(){
        // return new RateDiscountPolicy();
        return new FixDiscountPolicy();
    }
}
