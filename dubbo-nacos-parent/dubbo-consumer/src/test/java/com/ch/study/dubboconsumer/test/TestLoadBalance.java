//package com.ch.study.dubboconsumer.test;
//
//import com.ch.study.publicapi.service.InfoService;
//import org.junit.Test;
//import org.apache.dubbo.common.constants.LoadbalanceRules;
//import org.apache.dubbo.config.annotation.DubboReference;
//
//public class TestLoadBalance extends ApplicationTest {
//
//    //dumbo�ṩ��Referenceע�⣬���ڵ���Զ�̷���
//    @DubboReference(version = "1.0.0", loadbalance = LoadbalanceRules.LEAST_ACTIVE)
//    private InfoService infoService;
//
//    @Test
//    public void getInfo() {
//        for (int i = 0; i < 20; i++) {
//            infoService.getInfo();
//        }
//    }
//}
