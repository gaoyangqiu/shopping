package com.car.shopping.service.Impl;

import com.car.shopping.common.RestResult;
import com.car.shopping.dao.*;
import com.car.shopping.entity.TbBicycle;
import com.car.shopping.entity.TbOrder;
import com.car.shopping.entity.TbUsers;
import com.car.shopping.enumt.OrderStatus;
import com.car.shopping.service.OrderService;
import com.car.shopping.vo.OrderVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.List;

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

    @Autowired
    private TbBicycleMapper bicycleMapper;
    @Autowired
    private TbBicycleTypeMapper tbBicycleTypeMapper;

    @Autowired
    private TbPlacementMapper tbPlacementMapper;
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

    @Override
    public RestResult findOrderByUserId(Integer page,Integer limit,int i) {
        RestResult resultUtil = new RestResult();
        PageHelper.startPage(page, limit);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<TbOrder> orders=orderMapper.findOrderByUserIdAndStatus(i,OrderStatus.YIGUIHUAN.getStatus());
        PageInfo<TbOrder> pageInfo = new PageInfo<TbOrder>(orders);
        List<OrderVo> orderVos= Lists.newArrayList();
        if (!CollectionUtils.isEmpty(orders)){
            for (TbOrder order : orders) {
                OrderVo vo=new OrderVo();
                BeanUtils.copyProperties(order,vo);
                TbBicycle bicycle =bicycleMapper.selectByPrimaryKey(order.getBicycleId().intValue());
                vo.setBicycleTypeName(tbBicycleTypeMapper.selectByPrimaryKey(bicycle.getType()).getName());
                vo.setBicycleNumber(bicycle.getNumber().toString());
                vo.setStatusName(OrderStatus.getOrderStatus(order.getStatus()).getDesc());
                vo.setTimeShow(order.getTime().toString()+"小时");
                vo.setStartTimeShow(formatter.format(order.getStartTime()));
                vo.setEndTimeShow(formatter.format(order.getEndTime()));
                orderVos.add(vo);
            }
        }
        resultUtil.setCode("0");
        resultUtil.setCount(pageInfo.getTotal());
        resultUtil.setData(orderVos);
        resultUtil.setCount(pageInfo.getTotal());
        return  resultUtil;
    }
}
