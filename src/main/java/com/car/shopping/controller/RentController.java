package com.car.shopping.controller;

import com.car.shopping.common.RestResult;
import com.car.shopping.entity.TbUsers;
import com.car.shopping.service.RentService;
import com.car.shopping.vo.BicycleVo;
import com.car.shopping.vo.CreateRentVo;
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
public class RentController {

    @Autowired
    private RentService rentService;

    @RequestMapping("rent")
    public String rent (){
        return "rent";
    }


    @ResponseBody
    @RequestMapping("/rentList")
    public List<BicycleVo> getRentList(){
        return rentService.getRentListAll();
    }

    @ResponseBody
    @RequestMapping("/createRent")
    public RestResult createRent(@RequestBody CreateRentVo rentVo, HttpSession session){
        TbUsers user=(TbUsers)session.getAttribute("user");
        Long userId=user.getUid();
        rentVo.setUserId(userId.intValue());
        return rentService.createRent(rentVo);
    }
}
