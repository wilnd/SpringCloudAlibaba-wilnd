package com.ch.study.seata.storage.service.impl;


import com.ch.study.seata.storage.dao.StorageDao;
import com.ch.study.seataapi.service.storage.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.LoadbalanceRules;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
@DubboService(version = "1.0.0", loadbalance = LoadbalanceRules.LEAST_ACTIVE)
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageDao storageDao;

    // 扣减库存
    @Override
    public void decrease(Long productId, Integer count) {
        log.info("------->storage-service中扣减库存开始");
        storageDao.decrease(productId,count);
        log.info("------->storage-service中扣减库存结束");
    }
}