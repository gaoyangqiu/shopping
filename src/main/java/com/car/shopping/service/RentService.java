package com.car.shopping.service;

import com.car.shopping.common.RestResult;
import com.car.shopping.common.ResultUtil;
import com.car.shopping.vo.BicycleVo;
import com.car.shopping.vo.CreateRentVo;

import java.text.ParseException;
import java.util.List;

/**
 * @Author: qgy
 * @Date: 2019/4/5 2:24
 * @Description:
 */
public interface RentService {
    RestResult getRentListAll(Integer page, Integer limit);

    RestResult createRent(CreateRentVo rentVo) throws ParseException;
}
