package com.ch.study.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.ch.study.model.auto.ConfigInfo;
import com.ch.study.service.IConfigInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * config_info 前端控制器
 * </p>
 *
 * @author astupidcoder
 * @since 2024-01-31
 */
@Slf4j
@RestController
@RequestMapping("/config-info")
public class ConfigInfoController {

    @Autowired
    private IConfigInfoService configInfoService;

    @PostMapping("/getConfigInfo")
    @SentinelResource(value = "common-resource", blockHandler = "getConfigInfoBlock", fallback = "getConfigInfoFallback")
    public ConfigInfo getConfigInfo() {
        log.info("getConfigInfo success");
        return configInfoService.getById(1);
    }

    @PostMapping("/findAllConfig")
    @SentinelResource(value = "findAllConfig", blockHandler = "findAllConfigBlock", fallback = "findAllConfigFallback")
    public List<ConfigInfo> findAllConfig() {
        log.info("findAllConfig success");
        return configInfoService.findAllConfig();
    }

    public List<ConfigInfo> findAllConfigBlock() {
        log.info("==findAllConfigBlock execute==");
        return null;
    }

    public List<ConfigInfo> findAllConfigFallback() {
        log.info("==findAllConfigFallback execute==");
        return null;
    }

    // Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
    public ConfigInfo getConfigInfoBlock() {
        log.info("==getConfigInfoBlock execute==");
        return new ConfigInfo();
    }

    // Fallback 函数，函数签名与原函数一致或加一个 Throwable 类型的参数.
    public ConfigInfo getConfigInfoFallback() {
        log.info("==getConfigInfoFallback execute==");
        return new ConfigInfo();
    }
}
