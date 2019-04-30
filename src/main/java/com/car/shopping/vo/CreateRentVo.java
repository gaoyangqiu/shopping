package com.car.shopping.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author: qgy
 * @Date: 2019/3/31 21:06
 * @Description:
 */

public class CreateRentVo {

    private String endTime;

    private String startTime;

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

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
