package com.ch.fun.home.provider.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * 自定义sink
 */
public interface MySink {
    String INPUT1 = "input1";

    @Input(MySink.INPUT1)
    SubscribableChannel input1();

    String INPUT2 = "input2";

    @Input(MySink.INPUT2)
    SubscribableChannel input2();
}