//package com.ch.study.dubboprovider.impl;
//
//
//import io.micrometer.core.instrument.Counter;
//import io.micrometer.core.instrument.Metrics;
//import org.springframework.stereotype.Service;
//
//import java.util.concurrent.atomic.AtomicInteger;
//
//@Service
//public class MonitorTestService {
//    private final AtomicInteger userGauge1 = Metrics.gauge("test.thread.pool.s1.size", new AtomicInteger(0));
//
//    private final AtomicInteger userGauge2 = Metrics.gauge("test.thread.pool.s2.size", new AtomicInteger(0));
//
//    private final Counter requestCounter = Metrics.counter("test.request.total", "task", "s1");
//
//    public AtomicInteger getGauge1() {
//        return userGauge1;
//    }
//
//    public AtomicInteger getGauge2() {
//        return userGauge2;
//    }
//
//    public Counter getRequestCounter() {
//        return requestCounter;
//    }
//}
