package com.car.shopping.service.Impl;

import com.car.shopping.dao.TbUsersMapper;
import com.car.shopping.entity.TbUsers;
import com.car.shopping.entity.TbUsersExample;
import com.car.shopping.service.UserService;
import com.car.shopping.vo.LoginVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: 72038667
 * @Date: 2019/4/4 12:43
 * @Version: 1.0
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TbUsersMapper usersMapper;
    @Override
    public TbUsers findUserByPhoneAndPassword(LoginVo loginVo) {
        TbUsersExample example=new TbUsersExample();
        TbUsersExample.Criteria criteria= example.createCriteria();
        if(StringUtils.isNotBlank(loginVo.getPassword())){
            criteria.andPasswordEqualTo(loginVo.getPassword());
        }
        if (StringUtils.isNotBlank(loginVo.getPhone())){
            criteria.andPhoneEqualTo(loginVo.getPhone());
        }
        List<TbUsers> tbUsersList=usersMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(tbUsersList)){
            return null;
        }
        return tbUsersList.get(0);
    }

    @Override
    public void updateUserInfo(TbUsers users) {
        users.setCreateTime(new Date());
        users.setStatus("1");
        usersMapper.updateByPrimaryKeySelective(users);
    }
}
