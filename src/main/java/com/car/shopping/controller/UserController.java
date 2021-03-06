package com.car.shopping.controller;

import com.car.shopping.common.RestResult;
import com.car.shopping.entity.TbUsers;
import com.car.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @Author: 72038667
 * @Date: 2019/4/4 15:34
 * @Version: 1.0
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping("userInfo")
    public String userInfo (){
        return "userInfo";
    }


    @RequestMapping("/updateUserInfo")
    @ResponseBody
    public RestResult updateUserInfo(@RequestBody TbUsers users, HttpSession session) {
        userService.updateUserInfo(users);
        session.setAttribute("user",users);
        return RestResult.success();
    }

    @RequestMapping("regist")
    public String regist (){
        return "regist";
    }


    @RequestMapping("/registUserInfo")
    @ResponseBody
    public RestResult registUserInfo(@RequestBody TbUsers users) {
        userService.registUserInfo(users);
        return RestResult.success();
    }


    @RequestMapping("/updateBalance")
    @ResponseBody
    public RestResult updateBalance(Integer balance, HttpSession session) {
        TbUsers user= (TbUsers) session.getAttribute("user");
        userService.updateBalance(balance,user.getUid());
        user.setBalance(user.getBalance()+balance);
        session.setAttribute("user",user);
        return RestResult.success();
    }

}
