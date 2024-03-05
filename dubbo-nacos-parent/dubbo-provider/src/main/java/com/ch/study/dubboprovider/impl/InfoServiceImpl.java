package com.ch.study.dubboprovider.impl;

import com.ch.study.publicapi.service.InfoService;
import org.apache.dubbo.common.constants.LoadbalanceRules;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@DubboService(version = "1.0.0", loadbalance = LoadbalanceRules.LEAST_ACTIVE)
public class InfoServiceImpl implements InfoService {

    @Value("${dubbo.protocol.port}")
    private String port;

    public String getInfo(Integer num) {
        return port;
    }

}
