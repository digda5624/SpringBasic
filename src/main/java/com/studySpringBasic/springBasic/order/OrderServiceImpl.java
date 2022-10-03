package com.studySpringBasic.springBasic.order;

import com.studySpringBasic.springBasic.discount.DiscountPolicy;
import com.studySpringBasic.springBasic.discount.FixDiscountPolicy;
import com.studySpringBasic.springBasic.discount.RateDiscountPolicy;
import com.studySpringBasic.springBasic.entity.Member;
import com.studySpringBasic.springBasic.entity.Order;
import com.studySpringBasic.springBasic.member.repository.MemberRepository;
import com.studySpringBasic.springBasic.member.repository.MemoryMemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Getter
@Component
public class OrderServiceImpl implements OrderService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    /**
     * 현재 추상클래스인 DiscountPolicy 와 구체 클래스에도 의존하고 있다. DIP 를 위반
     * 정책을 변경하는 순간 코드 또한 변경을 해야한다. OCP 위반
     *
     * 이유? 정책에 대한 내용을 직접 수행 하는 것이다.
     * 예를 들면 배우는 배역에만 집중해야지 상대 배우를 섭외하고 할당하는 것 까지 책임을 질 필요가 없다.
     *
     * 따라서 어플리케이션을 구성하는 부분을 따로 Config 클래스로 뺀다.
     */
//    DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    /**
     * 따라서 인터페이스만 의존하게 해서 만들자 현재 DIP 를 지킨 상태이다.
     *
     * 이제 의존관계에 대한 고민은 외부에 맡기고 OrderService는 실행에만 집중한다.
     * 관심사가 분리되었다.
     *
     * AppConfig 에 의해서 OrderServiceImpl 의 의존관계가 주입이 된 것이다.
     * 이를 DI (Dependency Injection) 즉, 의존 관계 주입(의존성 주입) 이라고 부른다.
     */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
