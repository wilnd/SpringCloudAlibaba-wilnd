package com.ch.study.dubbocunsomer.controller;

import com.ch.study.publicapi.service.InfoService;
import org.apache.dubbo.common.constants.LoadbalanceRules;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.support.Parameter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class InfoController {

    //dumbo提供的Reference注解，用于调用远程服务
    @DubboReference(version = "1.0.0", loadbalance = LoadbalanceRules.LEAST_ACTIVE)
    private InfoService infoService;

    @GetMapping("/getInfo")
    public Map<String, Integer> getInfo(int num) {
        Map<String, Integer> weightMap = new HashMap();
        for (int i = 0; i < num; i++) {
            String weight = infoService.getInfo(i);
            Integer number = weightMap.get(weight);
            if (number == null) {
                weightMap.put(weight, 1);
            } else {
                weightMap.put(weight, number + 1);
            }
        }
        return weightMap;
    }

}
