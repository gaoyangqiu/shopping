package com.car.shopping.service;

import com.car.shopping.common.RestResult;
import com.car.shopping.entity.TbPlacement;
import com.car.shopping.vo.OrderVo;

import java.util.List;

/**
 * @Author: qgy
 * @Date: 2019/4/5 3:37
 * @Description:
 */
public interface ReturnService {
    RestResult findOrderByUserId(Integer page, Integer limit, int i);

    void returnPay(Integer placement,Integer orderId);

    List<TbPlacement> placements();

}
