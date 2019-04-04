package com.car.shopping.service;

/**
 * @Author: qgy
 * @Date: 2019/4/5 3:56
 * @Description:
 */
public interface OrderService {
    void orderPay(Integer orderId, Long userId);
}
