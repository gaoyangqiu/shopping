package com.car.shopping.vo;

import com.car.shopping.entity.TbUsers;
import lombok.Data;

/**
 * @Author: qgy
 * @Date: 2019/4/5 1:39
 * @Description:
 */
@Data
public class UserVo extends TbUsers {
    private Integer balance;
}
