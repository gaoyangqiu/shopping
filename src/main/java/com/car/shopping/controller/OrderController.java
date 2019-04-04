package com.car.shopping.controller;

import com.car.shopping.common.RestResult;
import com.car.shopping.entity.TbUsers;
import com.car.shopping.service.OrderService;
import com.car.shopping.service.ReturnService;
import com.car.shopping.vo.OrderVo;
import com.car.shopping.vo.PayVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ReturnService returnService;
    @RequestMapping("/order")
    public String index(){
        return "order";
    }


    @RequestMapping("/orderList")
    @ResponseBody
    public List<OrderVo> orderList(HttpSession session){
        TbUsers user=(TbUsers)session.getAttribute("user");
        Long userId=user.getUid();
        return returnService.findOrderByUserId(userId.intValue());
    }

    @RequestMapping("/orderPay")
    @ResponseBody
    public RestResult orderPay(@RequestBody PayVo vo,HttpSession session){

        TbUsers user=(TbUsers)session.getAttribute("user");
        Long userId=user.getUid();
        orderService.orderPay(vo.getOrderId(),userId);
        return RestResult.success();
    }
}
