package com.car.shopping.dao;

import com.car.shopping.entity.TbOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbOrder record);

    int insertSelective(TbOrder record);

    TbOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbOrder record);

    int updateByPrimaryKey(TbOrder record);

    List<TbOrder> findOrderByUserIdAndStatus(@Param("userId") int userId,@Param("status")Integer status);
}