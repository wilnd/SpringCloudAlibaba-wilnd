package com.ch.study.seataapi.service.storage;


import com.ch.study.seataapi.util.CommonResult;

public interface StorageService {

    void decrease(Long productId, Integer count);

}