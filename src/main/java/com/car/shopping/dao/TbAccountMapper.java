package com.car.shopping.dao;

import com.car.shopping.entity.TbAccount;
import com.car.shopping.entity.TbAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbAccountMapper {
    int countByExample(TbAccountExample example);

    int deleteByExample(TbAccountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbAccount record);

    int insertSelective(TbAccount record);

    List<TbAccount> selectByExample(TbAccountExample example);

    TbAccount selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbAccount record, @Param("example") TbAccountExample example);

    int updateByExample(@Param("record") TbAccount record, @Param("example") TbAccountExample example);

    int updateByPrimaryKeySelective(TbAccount record);

    int updateByPrimaryKey(TbAccount record);
}