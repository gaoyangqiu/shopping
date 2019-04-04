package com.car.shopping.service;

import com.car.shopping.vo.OrderVo;

import java.util.List;

/**
 * @Author: qgy
 * @Date: 2019/4/5 3:37
 * @Description:
 */
public interface ReturnService {
    List<OrderVo> findOrderByUserId(int i);

    void returnPay(Integer orderId);
}
