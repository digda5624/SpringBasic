package com.studySpringBasic.springBasic.discount;

import com.studySpringBasic.springBasic.entity.Member;

public interface DiscountPolicy {

    /**
     * 할인 금액을 알려준다.
     */
    int discount(Member member, int price);
}
