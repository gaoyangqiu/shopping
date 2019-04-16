package com.car.shopping.vo;

import com.car.shopping.entity.TbUsers;
import lombok.Data;

/**
 * @Author: qgy
 * @Date: 2019/4/5 1:39
 * @Description:
 */

public class UserVo extends TbUsers {
    private Integer balance;

    @Override
    public Integer getBalance() {
        return balance;
    }

    @Override
    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
