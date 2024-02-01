package com.ch.study.controller;


import com.ch.study.model.auto.ConfigInfo;
import com.ch.study.service.IConfigInfoService;
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
@RestController
@RequestMapping("/config-info")
public class ConfigInfoController {

    @Autowired
    private IConfigInfoService configInfoService;

    @PostMapping("/getConfigInfo")
    public ConfigInfo getConfigInfo() {
        return configInfoService.getById(1);
    }

    @PostMapping("/findAllConfig")
    public List<ConfigInfo> findAllConfig() {
        return configInfoService.findAllConfig();
    }

}
