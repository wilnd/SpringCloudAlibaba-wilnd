package com.ch.study.fun.order.controller;


import com.ch.study.fun.common.domain.Order;
import com.ch.study.fun.common.util.CommonResult;
import com.ch.study.fun.order.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    private OrderService orderService;


    @GetMapping("/order/create")
    public CommonResult create(Order order) {
        orderService.create(order);
        return new CommonResult(200, "订单创建成功");
    }


}
