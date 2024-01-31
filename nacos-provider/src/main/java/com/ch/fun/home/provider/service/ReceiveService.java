package com.ch.fun.home.provider.service;

import com.ch.fun.home.provider.message.MySink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.stereotype.Service;

@Service
public class ReceiveService {

    /**
     * spring cloud stream里面通过 Sink 接收消息
     */
    @Autowired
    private MySink sink;

    @StreamListener(value = MySink.INPUT1)
    public void listener1(String message) {
        System.out.println("test-group1=" + message);
        throw new IllegalArgumentException("抛异常"); //模拟异常
    }

    /**
     * 局部异常处理
     * inputChannel格式：${topic-name}.{group-name}.errors
     * 分别对应我们在配置文件配置的destination和group
     *
     * @param message
     */
    @ServiceActivator(inputChannel = "test-topic1.test-group1.errors")
    public void handleError(ErrorMessage message) {
        Throwable throwable = message.getPayload();
        System.out.println("截获异常" + throwable);

        Message<?> originalMessage = message.getOriginalMessage();
        assert originalMessage != null;

        System.out.println("原始消息体 = " + new String((byte[]) originalMessage.getPayload()));
    }

    /**
     * 全局异常处理
     *
     * @param message
     */
    @StreamListener("errorChannel")
    public void error(Message<?> message) {
        ErrorMessage errorMessage = (ErrorMessage) message;
        System.out.println("Handling ERROR: " + errorMessage);
    }


    @StreamListener(value = MySink.INPUT2)
    public void listener2(String message) {
        System.out.println("test-group2=" + message);
    }
}
