package com.car.shopping.dao;

import com.car.shopping.entity.TbBicycle;

import java.util.List;

public interface TbBicycleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbBicycle record);

    int insertSelective(TbBicycle record);

    TbBicycle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbBicycle record);

    int updateByPrimaryKey(TbBicycle record);

    List<TbBicycle> getRentListAll();

}