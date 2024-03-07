package com.ch.study.seata.order.controller;


import com.ch.study.seataapi.domain.Order;
import com.ch.study.seataapi.service.order.OrderService;
import com.ch.study.seataapi.util.CommonResult;
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
