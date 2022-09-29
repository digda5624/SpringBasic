package com.studySpringBasic.springBasic.order;

import com.studySpringBasic.springBasic.entity.Order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
