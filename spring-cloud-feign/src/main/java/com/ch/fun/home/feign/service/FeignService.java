package com.ch.fun.home.feign.service;

import com.ch.fun.home.feign.service.fallback.FeignServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-provider", fallback = FeignServiceFallback.class)
public interface FeignService {

    @GetMapping(value = "/test/{message}")
    String test(@PathVariable("message") String message);

}
