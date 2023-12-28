package com.ch.fun.home.feign.service.fallback;

import com.ch.fun.home.feign.service.FeignService;
import org.springframework.stereotype.Component;

@Component
public class FeignServiceFallback implements FeignService {

    @Override
    public String test(String message) {
        return "test fallback";
    }
}
