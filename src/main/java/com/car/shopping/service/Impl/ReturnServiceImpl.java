package com.car.shopping.service.Impl;

import com.car.shopping.common.RestResult;
import com.car.shopping.dao.TbBicycleMapper;
import com.car.shopping.dao.TbBicycleTypeMapper;
import com.car.shopping.dao.TbOrderMapper;
import com.car.shopping.dao.TbPlacementMapper;
import com.car.shopping.entity.TbBicycle;
import com.car.shopping.entity.TbOrder;
import com.car.shopping.entity.TbPlacement;
import com.car.shopping.enumt.BicycleStatus;
import com.car.shopping.enumt.BicycleType;
import com.car.shopping.enumt.OrderStatus;
import com.car.shopping.service.ReturnService;
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
 * @Date: 2019/4/5 3:37
 * @Description:
 */
@Service
public class ReturnServiceImpl implements ReturnService {
    @Autowired
    private TbOrderMapper orderMapper;
    @Autowired
    private TbBicycleMapper bicycleMapper;
    @Autowired
    private TbBicycleTypeMapper tbBicycleTypeMapper;

    @Autowired
    private TbPlacementMapper tbPlacementMapper;
    @Override
    public RestResult findOrderByUserId(Integer page,Integer limit,int i) {
        RestResult resultUtil = new RestResult();
        PageHelper.startPage(page, limit);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<TbOrder> orders=orderMapper.findOrderByUserIdAndStatus(i,OrderStatus.YIZHIFU.getStatus());
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

    @Override
    public void returnPay(Integer placement,Integer orderId) {
        TbOrder order=orderMapper.selectByPrimaryKey(orderId.longValue());
        TbBicycle bicycle=bicycleMapper.selectByPrimaryKey(order.getBicycleId().intValue());
        TbPlacement placement1=tbPlacementMapper.selectByPrimaryKey(placement);
        bicycle.setPlacement(placement1.getId());
/*        bicycle.setStatus(BicycleStatus.YIGUIHUAN.getStatus());*/
        //存放点的数量+1
        placement1.setCount(placement1.getCount()+1);
        order.setStatus(OrderStatus.YIGUIHUAN.getStatus());
        orderMapper.updateByPrimaryKeySelective(order);
        tbPlacementMapper.updateByPrimaryKeySelective(placement1);
        bicycleMapper.updateByPrimaryKeySelective(bicycle);
    }

    @Override
    public List<TbPlacement> placements() {
        List<TbPlacement> tbPlacements=tbPlacementMapper.selectAll();
        return tbPlacements;
    }
}
