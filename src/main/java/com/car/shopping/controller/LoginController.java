package com.car.shopping.controller;

import com.car.shopping.common.ErrorMessage;
import com.car.shopping.common.RestResult;
import com.car.shopping.entity.TbUsers;
import com.car.shopping.service.UserService;
import com.car.shopping.vo.LoginVo;
import com.car.shopping.vo.MenusVo;
import com.car.shopping.vo.UserVo;
import com.google.code.kaptcha.Producer;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
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
    @Autowired
    private Producer captchaProducer = null;
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
        RestResult restResult=new RestResult();
        if (null==user){
            restResult.setCode(ErrorMessage.USER_NOT_EXIST.getCode());
            restResult.setErrorMessage(ErrorMessage.USER_NOT_EXIST.getDesc());
            return restResult;
        }
        if(StringUtils.isBlank(loginVo.getVcode())|| !loginVo.getVcode().toLowerCase().equals(session.getAttribute("kaptcha"))){
            restResult.setCode(ErrorMessage.VCODEEXCEPTION.getCode());
            restResult.setErrorMessage(ErrorMessage.VCODEEXCEPTION.getDesc());
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
        MenusVo menusVo4=new MenusVo("历史记录","/order");
        List<MenusVo> menusVoList=Lists.newArrayList(menusVo1,menusVo2,menusVo3,menusVo4);
        return menusVoList;
    }

    @RequestMapping("loginOut")
    public String loginOut(HttpSession session){
        session.invalidate();
        return "login";
    }


    /**
     * 验证码
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    @RequestMapping("vcode")
    public void vcode(HttpServletRequest req, HttpServletResponse resp,HttpSession session) throws Exception {
        String text = captchaProducer.createText();
        BufferedImage image = captchaProducer.createImage(text);
        session.setAttribute("kaptcha", text);
        ImageIO.write(image, "JPEG", resp.getOutputStream());
    }
}
