package com.car.shopping.vo;

import lombok.Data;

/**
 * @Author: qgy
 * @Date: 2019/3/31 21:06
 * @Description:
 */

public class CreateRentVo {

    private Integer time;

    private Integer bicycleId;

    private Integer userId;

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getBicycleId() {
        return bicycleId;
    }

    public void setBicycleId(Integer bicycleId) {
        this.bicycleId = bicycleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
