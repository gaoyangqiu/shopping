package com.car.shopping.dao;

import com.car.shopping.entity.TbBicycle;
import com.car.shopping.entity.TbBicycleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbBicycleMapper {
    int countByExample(TbBicycleExample example);

    int deleteByExample(TbBicycleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbBicycle record);

    int insertSelective(TbBicycle record);

    List<TbBicycle> selectByExample(TbBicycleExample example);

    TbBicycle selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbBicycle record, @Param("example") TbBicycleExample example);

    int updateByExample(@Param("record") TbBicycle record, @Param("example") TbBicycleExample example);

    int updateByPrimaryKeySelective(TbBicycle record);

    int updateByPrimaryKey(TbBicycle record);
}