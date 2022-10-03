package com.studySpringBasic.springBasic.strategy;

import com.studySpringBasic.springBasic.config.AutoAppConfig;
import com.studySpringBasic.springBasic.config.ConfigSpring;
import com.studySpringBasic.springBasic.discount.DiscountPolicy;
import com.studySpringBasic.springBasic.discount.Strategy;
import com.studySpringBasic.springBasic.entity.Grade;
import com.studySpringBasic.springBasic.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class DiscountServiceTest {

    @Test
    @DisplayName("[sucess] 여러개의_전략패턴_받아들이기")
    public void 여러개의_전략패턴_받아들이기(){
        // given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        // when
        DiscountService bean = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "현승구", Grade.VIP);

        // then
        int fixDiscount = bean.discount(member, 20000, Strategy.FIX_POLICY);
        int rateDiscount = bean.discount(member, 20000, Strategy.RATE_POLICY);

        Assertions.assertThat(fixDiscount).isEqualTo(1000);
        Assertions.assertThat(rateDiscount).isEqualTo(2000);
    }

    public static class DiscountService{
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, Strategy strategy){
            DiscountPolicy discountPolicy = policyMap.get(strategy.getPolicy());
            return discountPolicy.discount(member, price);
        }
    }
}
