package com.car.shopping.dao;

import com.car.shopping.entity.TbPlacement;

import java.util.List;

public interface TbPlacementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbPlacement record);

    int insertSelective(TbPlacement record);

    TbPlacement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbPlacement record);

    int updateByPrimaryKey(TbPlacement record);

    List<TbPlacement> selectAll();

}