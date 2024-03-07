package com.ch.study.seata.storage;


import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan({"com.ch.study.fun.storage.dao"})
@EnableDiscoveryClient
@EnableAutoDataSourceProxy
public class SeataStorageServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeataStorageServiceApplication.class, args);
    }

}