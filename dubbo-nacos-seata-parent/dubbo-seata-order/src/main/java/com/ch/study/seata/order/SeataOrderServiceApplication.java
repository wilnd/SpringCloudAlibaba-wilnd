package com.ch.study.seata.order;


import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan({"com.ch.study.fun.order.dao"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.ch.study.fun")
@EnableAutoDataSourceProxy
public class SeataOrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeataOrderServiceApplication.class, args);
    }

}
