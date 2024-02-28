package com.ch.study.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.ch.study.common.CommonResponse;
import com.ch.study.common.ResponseUtil;
import com.ch.study.model.auto.ConfigInfo;
import com.ch.study.service.IConfigInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
    @ResponseBody
    @SentinelResource(value = "common-resource", blockHandler = "getConfigInfoBlock", fallback = "getConfigInfoFallback")
    public CommonResponse getConfigInfo() {
        log.info("getConfigInfo success");
        return ResponseUtil.success(configInfoService.getById(1));
    }

    @ResponseBody
    @PostMapping("/findAllConfig")
    @SentinelResource(value = "common-resource", blockHandler = "findAllConfigBlock", fallback = "findAllConfigFallback")
    public CommonResponse findAllConfig() {
        log.info("findAllConfig success");
        return ResponseUtil.success(configInfoService.findAllConfig());
    }

    @PostMapping("/getConfigInfo2")
    @ResponseBody
    public CommonResponse getConfigInfo2() {
        log.info("getConfigInfo2 success");
        return ResponseUtil.success(configInfoService.getById(1));
    }


    public CommonResponse findAllConfigBlock() {
        log.info("==findAllConfigBlock execute==");
        return null;
    }

    public CommonResponse findAllConfigFallback() {
        log.info("==findAllConfigFallback execute==");
        return null;
    }

    // Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
    public CommonResponse getConfigInfoBlock() {
        log.info("==getConfigInfoBlock execute==");
        return null;
    }

    // Fallback 函数，函数签名与原函数一致或加一个 Throwable 类型的参数.
    public CommonResponse getConfigInfoFallback() {
        log.info("==getConfigInfoFallback execute==");
        return null;
    }
}
