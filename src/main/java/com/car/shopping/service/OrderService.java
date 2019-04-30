package com.car.shopping.service;

import com.car.shopping.common.RestResult;
import com.car.shopping.vo.OrderVo;

import java.util.List;

/**
 * @Author: qgy
 * @Date: 2019/4/5 3:56
 * @Description:
 */
public interface OrderService {
    void orderPay(Integer orderId, Long userId);

    RestResult findOrderByUserId(Integer page, Integer limit, int i);
}
