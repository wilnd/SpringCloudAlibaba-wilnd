package com.ch.study.dubboprovider.impl;

import com.ch.study.publicapi.service.InfoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

@Component
@DubboService
public class InfoServiceImpl implements InfoService {
    public String getInfo() {
        return "hello，这里是dubbo-provider模块！";
    }
}
