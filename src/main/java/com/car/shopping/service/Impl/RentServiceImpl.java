package com.car.shopping.service.Impl;

import com.car.shopping.common.RestResult;
import com.car.shopping.dao.TbBicycleMapper;
import com.car.shopping.dao.TbOrderMapper;
import com.car.shopping.entity.TbBicycle;
import com.car.shopping.entity.TbOrder;
import com.car.shopping.enumt.BicycleType;
import com.car.shopping.enumt.OrderStatus;
import com.car.shopping.service.RentService;
import com.car.shopping.vo.BicycleVo;
import com.car.shopping.vo.CreateRentVo;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: qgy
 * @Date: 2019/4/5 2:25
 * @Description:
 */
@Service
public class RentServiceImpl implements RentService{
    @Autowired
    private TbBicycleMapper tbBicycleMapper;

    @Autowired
    private TbOrderMapper tbOrderMapper;
    @Override
    public List<BicycleVo> getRentListAll() {
        List<TbBicycle> tbBicycles=tbBicycleMapper.getRentListAll();
        List<BicycleVo> bicycleVos= Lists.newArrayList();
        for (TbBicycle tbBicycle : tbBicycles) {
            BicycleVo vo=new BicycleVo();
            BeanUtils.copyProperties(tbBicycle,vo);
            vo.setTypeName(BicycleType.getBicycleType(tbBicycle.getType()).getDesc());
            bicycleVos.add(vo);
        }
        return bicycleVos;
    }

    @Override
    public RestResult createRent(CreateRentVo rentVo) {
        TbBicycle bicycle=tbBicycleMapper.selectByPrimaryKey(rentVo.getBicycleId());
        TbOrder order =new TbOrder();
        order.setStatus(OrderStatus.WEIZHIFU.getStatus());
        order.setBicycleId(rentVo.getBicycleId().longValue());
        order.setDflag(0);
        order.setTime(rentVo.getTime());
        order.setUserId(rentVo.getUserId().longValue());
        order.setTotalPrice((bicycle.getPrice()*rentVo.getTime()));
        tbOrderMapper.insertSelective(order);
        return RestResult.success();
    }
}
