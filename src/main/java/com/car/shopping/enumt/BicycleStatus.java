package com.car.shopping.enumt;

/**
 * @Author: qgy
 * @Date: 2019/4/19 22:52
 * @Description:
 */
public enum BicycleStatus {
    WEICHUZU("未出租",0),YICHUZU("已出租",1),YIGUIHUAN("已归还",2);

    private String name;

    private Integer status;

    BicycleStatus(String name, Integer status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public Integer getStatus() {
        return status;
    }

    public static BicycleStatus getBicycleType(Integer Type){
        for (BicycleStatus bicycleType : BicycleStatus.values()) {
            if (bicycleType.getStatus()==Type){
                return bicycleType;
            }
        }
        return null;
    }
}
