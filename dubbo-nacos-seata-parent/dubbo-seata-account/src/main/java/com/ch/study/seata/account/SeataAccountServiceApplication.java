package com.ch.study.seata.account;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan({"com.ch.study.account.dao"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.ch.study")
@EnableAutoDataSourceProxy
public class SeataAccountServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeataAccountServiceApplication.class, args);
    }

}