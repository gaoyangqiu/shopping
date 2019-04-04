package com.car.shopping.dao;

import com.car.shopping.entity.TbUsers;
import com.car.shopping.entity.TbUsersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbUsersMapper {
    int countByExample(TbUsersExample example);

    int deleteByExample(TbUsersExample example);

    int deleteByPrimaryKey(Long uid);

    int insert(TbUsers record);

    int insertSelective(TbUsers record);

    List<TbUsers> selectByExample(TbUsersExample example);

    TbUsers selectByPrimaryKey(Long uid);

    int updateByExampleSelective(@Param("record") TbUsers record, @Param("example") TbUsersExample example);

    int updateByExample(@Param("record") TbUsers record, @Param("example") TbUsersExample example);

    int updateByPrimaryKeySelective(TbUsers record);

    int updateByPrimaryKey(TbUsers record);
}