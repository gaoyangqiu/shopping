package com.car.shopping.service;

import com.car.shopping.entity.TbUsers;
import com.car.shopping.vo.LoginVo;
import com.car.shopping.vo.UserVo;

/**
 * @Author: 72038667
 * @Date: 2019/4/4 12:43
 * @Version: 1.0
 */
public interface UserService {
    TbUsers findUserByPhoneAndPassword(LoginVo loginVo);

    void updateUserInfo(TbUsers users);

    void registUserInfo(TbUsers users);
}
