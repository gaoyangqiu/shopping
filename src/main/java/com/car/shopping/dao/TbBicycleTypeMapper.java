package com.car.shopping.dao;

import com.car.shopping.entity.TbBicycleType;

public interface TbBicycleTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbBicycleType record);

    int insertSelective(TbBicycleType record);

    TbBicycleType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbBicycleType record);

    int updateByPrimaryKey(TbBicycleType record);
}