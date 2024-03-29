package com.ch.study.seata.order.service.impl;


import com.ch.study.seata.order.dao.OrderDao;
import com.ch.study.seataapi.domain.Order;
import com.ch.study.seataapi.service.account.AccountService;
import com.ch.study.seataapi.service.order.OrderService;
import com.ch.study.seataapi.service.storage.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.LoadbalanceRules;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@DubboService(version = "1.0.0", loadbalance = LoadbalanceRules.LEAST_ACTIVE)
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @DubboReference(version = "1.0.0", loadbalance = LoadbalanceRules.LEAST_ACTIVE)
    private StorageService storageService;

    @DubboReference(version = "1.0.0", loadbalance = LoadbalanceRules.LEAST_ACTIVE)
    private AccountService accountService;


    /**
     * 创建订单->调用库存服务扣减库存->调用账户服务扣减账户余额->修改订单状态
     */

    @GlobalTransactional(name = "ch-create-order",rollbackFor = Exception.class)
    @Override
    public void create(Order order){
        log.info("----->开始新建订单");
        //新建订单
        orderDao.create(order);

        //扣减库存
        log.info("----->订单微服务开始调用库存，做扣减Count");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("----->订单微服务开始调用库存，做扣减end");

        //扣减账户
        log.info("----->订单微服务开始调用账户，做扣减Money");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("----->订单微服务开始调用账户，做扣减end");

        //修改订单状态，从零到1代表已经完成
        log.info("----->修改订单状态开始");
        orderDao.update(order.getUserId(),0);
        log.info("----->修改订单状态结束");

        log.info("----->下订单结束了");

    }
}