package com.car.shopping.service.Impl;

import com.car.shopping.common.RestResult;
import com.car.shopping.common.ResultUtil;
import com.car.shopping.dao.*;
import com.car.shopping.entity.*;
import com.car.shopping.enumt.BicycleStatus;
import com.car.shopping.enumt.BicycleType;
import com.car.shopping.enumt.OrderStatus;
import com.car.shopping.service.RentService;
import com.car.shopping.vo.BicycleVo;
import com.car.shopping.vo.CreateRentVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private TbUsersMapper tbUsersMapper;

    @Autowired
    private TbOrderMapper tbOrderMapper;

    @Autowired
    private TbBicycleTypeMapper tbBicycleTypeMapper;

    @Autowired
    private TbPlacementMapper tbPlacementMapper;
    @Override
    public RestResult getRentListAll(Integer page,Integer limit) {
        RestResult resultUtil = new RestResult();
        PageHelper.startPage(page, limit);
        List<TbBicycle> tbBicycles=tbBicycleMapper.getRentListAll();
        PageInfo<TbBicycle> pageInfo = new PageInfo<TbBicycle>(tbBicycles);
        List<BicycleVo> bicycleVos= coverVo(tbBicycles);
        resultUtil.setCode("0");
        resultUtil.setCount(pageInfo.getTotal());
        resultUtil.setData(bicycleVos);
        resultUtil.setCount(pageInfo.getTotal());
        return resultUtil;
    }


    private List<BicycleVo> coverVo(List<TbBicycle> tbBicycles) {
        List<BicycleVo> bicycleVos= Lists.newArrayList();
        for (TbBicycle bicycle : tbBicycles) {
            BicycleVo vo=new BicycleVo();
            BeanUtils.copyProperties(bicycle,vo);
            TbBicycleType tbBicycleType=tbBicycleTypeMapper.selectByPrimaryKey(bicycle.getType());
            TbPlacement tbPlacement=tbPlacementMapper.selectByPrimaryKey(bicycle.getPlacement());
            vo.setTypeName(tbBicycleType.getName());
            vo.setPlacementName(tbPlacement.getName());
            vo.setStatusName(BicycleStatus.getBicycleType(bicycle.getStatus()).getName());
            bicycleVos.add(vo);
        }
        return bicycleVos;
    }


    @Override
    public RestResult createRent(CreateRentVo rentVo) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TbBicycle bicycle=tbBicycleMapper.selectByPrimaryKey(rentVo.getBicycleId());
        bicycle.setStatus(BicycleStatus.YICHUZU.getStatus());
        Date startDate=formatter.parse(rentVo.getStartTime());
        Date endTime=formatter.parse(rentVo.getEndTime());
        TbOrder order =new TbOrder();
        order.setStatus(OrderStatus.YIZHIFU.getStatus());
        order.setBicycleId(rentVo.getBicycleId().longValue());
        order.setDflag(0);
        order.setStartTime(startDate);
        order.setEndTime(endTime);
        Long temp=endTime.getTime()-startDate.getTime();
        Integer time=temp.intValue()/ 1000 / (60 * 60);
        order.setTime(time);//小时
        order.setUserId(rentVo.getUserId().longValue());
        order.setTotalPrice(new Long(bicycle.getPrice()*time));
        TbUsers users=tbUsersMapper.selectByPrimaryKey(rentVo.getUserId().longValue());
        users.setBalance(users.getBalance()-bicycle.getPrice()*time);
        TbPlacement tbPlacement=tbPlacementMapper.selectByPrimaryKey(bicycle.getPlacement());
        //放置点的单车数量-1
        tbPlacement.setCount(tbPlacement.getCount()-1);
        tbOrderMapper.insertSelective(order);
        tbBicycleMapper.updateByPrimaryKeySelective(bicycle);
        tbUsersMapper.updateByPrimaryKeySelective(users);
        tbPlacementMapper.updateByPrimaryKeySelective(tbPlacement);
        return RestResult.success();
    }
}
