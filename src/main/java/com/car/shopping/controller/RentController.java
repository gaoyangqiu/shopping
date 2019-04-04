package com.car.shopping.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 72038667
 * @Date: 2019/4/4 19:10
 * @Version: 1.0
 */
public class RentController {


    @RequestMapping("userInfo")
    public String userInfo (){
        return "userInfo";
    }
}
