package com.car.shopping.service;

import com.car.shopping.common.RestResult;
import com.car.shopping.entity.TbBicycle;
import com.car.shopping.vo.BicycleVo;
import com.car.shopping.vo.CreateRentVo;

import java.util.List;

/**
 * @Author: qgy
 * @Date: 2019/4/5 2:24
 * @Description:
 */
public interface RentService {
    List<BicycleVo> getRentListAll();

    RestResult createRent(CreateRentVo rentVo);
}
