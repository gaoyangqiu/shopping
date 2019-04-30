package com.car.shopping.vo;

import com.car.shopping.entity.TbBicycle;

/**
 * @Author: qgy
 * @Date: 2019/4/5 3:10
 * @Description:
 */

public class BicycleVo extends TbBicycle {
    private String typeName;

    private String placementName;

    private String statusName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getPlacementName() {
        return placementName;
    }

    public void setPlacementName(String placementName) {
        this.placementName = placementName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
