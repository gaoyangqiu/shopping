package com.car.shopping.vo;

import com.car.shopping.entity.TbOrder;

/**
 * @Author: qgy
 * @Date: 2019/3/31 22:28
 * @Description:
 */

public class OrderVo extends TbOrder {

    private String bicycleName;

    private String statusName;

    private String bicycleTypeName;

    private String timeShow;

    private String bicycleNumber;

    private String startTimeShow;

    private String endTimeShow;


    public String getStartTimeShow() {
        return startTimeShow;
    }

    public void setStartTimeShow(String startTimeShow) {
        this.startTimeShow = startTimeShow;
    }

    public String getEndTimeShow() {
        return endTimeShow;
    }

    public void setEndTimeShow(String endTimeShow) {
        this.endTimeShow = endTimeShow;
    }

    public String getBicycleNumber() {
        return bicycleNumber;
    }

    public void setBicycleNumber(String bicycleNumber) {
        this.bicycleNumber = bicycleNumber;
    }

    public String getBicycleName() {
        return bicycleName;
    }

    public void setBicycleName(String bicycleName) {
        this.bicycleName = bicycleName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getBicycleTypeName() {
        return bicycleTypeName;
    }

    public void setBicycleTypeName(String bicycleTypeName) {
        this.bicycleTypeName = bicycleTypeName;
    }

    public String getTimeShow() {
        return timeShow;
    }

    public void setTimeShow(String timeShow) {
        this.timeShow = timeShow;
    }
}
