package com.car.shopping.service.Impl;

import com.car.shopping.dao.TbBicycleMapper;
import com.car.shopping.dao.TbOrderMapper;
import com.car.shopping.entity.TbBicycle;
import com.car.shopping.entity.TbOrder;
import com.car.shopping.enumt.BicycleType;
import com.car.shopping.enumt.OrderStatus;
import com.car.shopping.service.ReturnService;
import com.car.shopping.vo.OrderVo;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author: qgy
 * @Date: 2019/4/5 3:37
 * @Description:
 */
@Service
public class ReturnServiceImpl implements ReturnService {
    @Autowired
    private TbOrderMapper orderMapper;
    @Autowired
    private TbBicycleMapper bicycleMapper;
    @Override
    public List<OrderVo> findOrderByUserId(int i) {
        List<TbOrder> orders=orderMapper.findOrderByUserId(i);
        List<OrderVo> orderVos= Lists.newArrayList();
        if (!CollectionUtils.isEmpty(orders)){
            for (TbOrder order : orders) {
                OrderVo vo=new OrderVo();
                BeanUtils.copyProperties(order,vo);
                TbBicycle bicycle =bicycleMapper.selectByPrimaryKey(order.getBicycleId().intValue());
                vo.setBicycleName(bicycle.getName());
                vo.setBicycleTypeName(BicycleType.getBicycleType(bicycle.getType()).getDesc());
                vo.setStatusName(OrderStatus.getOrderStatus(order.getStatus()).getDesc());
                vo.setTimeShow(order.getTime().toString()+"小时");
                orderVos.add(vo);
            }
        }
        return  orderVos;

    }

    @Override
    public void returnPay(Integer orderId) {
        TbOrder order=new TbOrder();
        order.setStatus(OrderStatus.YIGUIHUAN.getStatus());
        order.setId(orderId.longValue());
        orderMapper.updateByPrimaryKeySelective(order);
    }
}
