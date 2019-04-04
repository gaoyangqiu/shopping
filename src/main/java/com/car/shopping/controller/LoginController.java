package com.car.shopping.controller;

import com.car.shopping.common.ErrorMessage;
import com.car.shopping.common.RestResult;
import com.car.shopping.entity.TbUsers;
import com.car.shopping.service.UserService;
import com.car.shopping.vo.LoginVo;
import com.car.shopping.vo.MenusVo;
import com.car.shopping.vo.UserVo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: 72038667
 * @Date: 2019/4/4 12:13
 * @Version: 1.0
 */

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public String login(){
        return "login";
    }

    @RequestMapping("index")
    public String index(){
        return "index";
    }
    @RequestMapping("ajaxLogin")
    @ResponseBody
    public RestResult ajaxLogin(@RequestBody  LoginVo loginVo, HttpSession session){
        TbUsers user=userService.findUserByPhoneAndPassword(loginVo);
        if (null==user){
            RestResult restResult=new RestResult();
            restResult.setCode(ErrorMessage.FAIL.getCode());
            restResult.setErrorMessage(ErrorMessage.FAIL.getDesc());
            return restResult;
        }
        session.setAttribute("user",user);

        return RestResult.success();
    }
    @RequestMapping("menus")
    @ResponseBody
    public List<MenusVo> menus(){
        MenusVo menusVo1=new MenusVo("个人信息","/userInfo");
        MenusVo menusVo2=new MenusVo("租赁","/rent");
        MenusVo menusVo3=new MenusVo("归还","/return");
        MenusVo menusVo4=new MenusVo("订单","/order");
        List<MenusVo> menusVoList=Lists.newArrayList(menusVo1,menusVo2,menusVo3,menusVo4);
        return menusVoList;
    }

    @RequestMapping("loginOut")
    public String loginOut(HttpSession session){
        session.invalidate();
        return "login";
    }
}
