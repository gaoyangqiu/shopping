package com.car.shopping.vo;

import com.car.shopping.entity.TbBicycle;
import lombok.Data;

/**
 * @Author: qgy
 * @Date: 2019/4/5 3:10
 * @Description:
 */

public class BicycleVo extends TbBicycle{
    private String typeName;
    private String statusName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
