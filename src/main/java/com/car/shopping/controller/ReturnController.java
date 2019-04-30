package com.car.shopping.controller;

import com.car.shopping.common.RestResult;
import com.car.shopping.entity.TbPlacement;
import com.car.shopping.entity.TbUsers;
import com.car.shopping.service.RentService;
import com.car.shopping.service.ReturnService;
import com.car.shopping.vo.BicycleVo;
import com.car.shopping.vo.CreateRentVo;
import com.car.shopping.vo.OrderVo;
import com.car.shopping.vo.PayVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: 72038667
 * @Date: 2019/4/4 19:10
 * @Version: 1.0
 */
@Controller
public class ReturnController {
    @Autowired
    private ReturnService returnService;
    @RequestMapping("/return")
    public String index(Model model){
        List<TbPlacement> tbPlacements=returnService.placements();
        model.addAttribute("tbPlacements",tbPlacements);
        return "return";
    }


    @RequestMapping("/returnList")
    @ResponseBody
    public RestResult returnList(Integer page,Integer limit,HttpSession session){
        TbUsers user=(TbUsers )session.getAttribute("user");
        Long userId=user.getUid();
        return returnService.findOrderByUserId(page,limit,userId.intValue());
    }

    @RequestMapping("/returnPay")
    @ResponseBody
    public RestResult returnPay(Integer placement,Integer orderId ){
        returnService.returnPay(placement,orderId);
        return RestResult.success();
    }
}
