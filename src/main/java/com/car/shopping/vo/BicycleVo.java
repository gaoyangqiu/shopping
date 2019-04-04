package com.car.shopping.vo;

import com.car.shopping.entity.TbBicycle;
import lombok.Data;

/**
 * @Author: qgy
 * @Date: 2019/4/5 3:10
 * @Description:
 */
@Data
public class BicycleVo extends TbBicycle{
    private String typeName;
    private String statusName;
}
