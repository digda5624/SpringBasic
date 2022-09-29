package com.studySpringBasic.springBasic.discount;

import com.studySpringBasic.springBasic.entity.Grade;
import com.studySpringBasic.springBasic.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("[sucess] 할인금액_적용")
    public void 할인금액_적용(){
        // given
        Member member = new Member(1L, "MemberA", Grade.VIP);
        // when
        int discount = rateDiscountPolicy.discount(member, 10000);

        // then
        Assertions.assertThat(discount).isEqualTo(1000);
    }
}