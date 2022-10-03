package com.studySpringBasic.springBasic.discount;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Strategy {
    FIX_POLICY("fixDiscountPolicy"),
    RATE_POLICY("rateDiscountPolicy");

    String policy;
}
