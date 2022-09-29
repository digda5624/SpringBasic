package com.studySpringBasic.springBasic.discount;

import com.studySpringBasic.springBasic.entity.Grade;
import com.studySpringBasic.springBasic.entity.Member;

public class RateDiscountPolicy implements DiscountPolicy {

    private final int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade().equals(Grade.VIP)){
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
