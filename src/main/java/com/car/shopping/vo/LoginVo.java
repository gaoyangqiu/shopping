package com.car.shopping.vo;

import lombok.Data;

/**
 * @Author: 72038667
 * @Date: 2019/4/4 12:40
 * @Version: 1.0
 */

public class LoginVo {

    private String phone;

    private String password;

    private String vcode;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }
}
