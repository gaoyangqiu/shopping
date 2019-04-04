package com.car.shopping.service.Impl;

import com.car.shopping.dao.TbOrderMapper;
import com.car.shopping.dao.TbUsersMapper;
import com.car.shopping.entity.TbOrder;
import com.car.shopping.entity.TbUsers;
import com.car.shopping.enumt.OrderStatus;
import com.car.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @Author: qgy
 * @Date: 2019/4/5 3:57
 * @Description:
 */
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private TbOrderMapper orderMapper;

    @Autowired
    private TbUsersMapper usersMapper;
    @Override
    public void orderPay(Integer orderId, Long userId) {
        TbOrder order=orderMapper.selectByPrimaryKey(orderId.longValue());
        order.setStatus(OrderStatus.YIZHIFU.getStatus());
        order.setId(orderId.longValue());
        orderMapper.updateByPrimaryKeySelective(order);
        //支付成功之后扣钱
        TbUsers user=usersMapper.selectByPrimaryKey(order.getUserId().longValue());
        user.setBalance(user.getBalance()-order.getTotalPrice().intValue());
        usersMapper.updateByPrimaryKeySelective(user);
    }
}
