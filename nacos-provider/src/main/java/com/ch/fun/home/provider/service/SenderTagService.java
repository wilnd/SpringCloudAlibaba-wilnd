package com.ch.fun.home.provider.service;

import com.ch.fun.home.provider.message.MySource;
import org.apache.rocketmq.common.message.MessageConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
public class SenderTagService {

    /**
     * spring cloud stream里面发消息通过 Source 发送
     */
    @Autowired
    private MySource source;

    /**
     * 发送消息的方法
     *
     */
    public <T> void sendObject(T msg, String tag) throws Exception {
        Message message = MessageBuilder.withPayload(msg)
                .setHeader(MessageConst.PROPERTY_TAGS, tag) //添加标签
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON) //内容类型
                .build();

        boolean flag = source.output2().send(message);
        System.out.println("sendObject==>" + flag);
    }
}
