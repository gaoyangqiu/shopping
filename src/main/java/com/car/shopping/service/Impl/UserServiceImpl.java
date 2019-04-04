package com.car.shopping.service.Impl;

import com.car.shopping.dao.TbAccountMapper;
import com.car.shopping.dao.TbUsersMapper;
import com.car.shopping.entity.TbAccount;
import com.car.shopping.entity.TbAccountExample;
import com.car.shopping.entity.TbUsers;
import com.car.shopping.entity.TbUsersExample;
import com.car.shopping.service.UserService;
import com.car.shopping.vo.LoginVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Autowired
    private TbAccountMapper accountMapper;
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
/*        UserVo vo=new UserVo();
        BeanUtils.copyProperties(tbUsersList.get(0),vo);
        TbAccountExample accountExample=new TbAccountExample();
        TbAccountExample.Criteria criteria1=accountExample.createCriteria();
        criteria1.andUserIdEqualTo(tbUsersList.get(0).getUid().intValue());
        List<TbAccount> accounts=accountMapper.selectByExample(accountExample);
        if (CollectionUtils.isNotEmpty(accounts)){
            vo.setBalance(accounts.get(0).getBalance());
        }*/
        return tbUsersList.get(0);
    }

    @Override
    public void updateUserInfo(TbUsers users) {
        users.setCreateTime(new Date());
        users.setStatus("1");
        usersMapper.updateByPrimaryKeySelective(users);
    }

    @Override

    public void registUserInfo(TbUsers users) {
        users.setStatus("1");
        users.setCreateTime(new Date());
        users.setSex("2");
        usersMapper.insertSelective(users);
    }
}
