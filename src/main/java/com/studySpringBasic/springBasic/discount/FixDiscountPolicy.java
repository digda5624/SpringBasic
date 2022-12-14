package com.studySpringBasic.springBasic.discount;

import com.studySpringBasic.springBasic.entity.Grade;
import com.studySpringBasic.springBasic.entity.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Qualifier("fixDiscountPolicy")
@Primary
public class FixDiscountPolicy implements DiscountPolicy {

    private final int discountFixAmount = 1000; // 1000원 할인 적용

    /**
     * discount VIP 에게만 1000원 할인 적용
     */
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade().equals(Grade.VIP)) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
