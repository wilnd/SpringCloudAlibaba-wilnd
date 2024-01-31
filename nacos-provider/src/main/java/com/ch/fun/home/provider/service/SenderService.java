package com.ch.fun.home.provider.service;

import com.ch.fun.home.provider.message.MySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class SenderService {

    /**
     * spring cloud stream里面发消息通过 Source 发送
     */
    @Autowired
    private MySource source;

    /**
     * 发送消息的方法
     *
     * @param message
     */
    public void sendMessage(String message) {
        boolean send1 = source.output1().send(MessageBuilder.withPayload(message).build());
        System.out.println("output1 result : " + send1);
        boolean send2 = source.output2().send(MessageBuilder.withPayload(message).build());
        System.out.println("output2 result : " + send2);
    }
}
