package com.ch.study.fun.storage.service;

public interface StorageService {

    // 扣减库存
    void decrease(Long productId, Integer count);
}