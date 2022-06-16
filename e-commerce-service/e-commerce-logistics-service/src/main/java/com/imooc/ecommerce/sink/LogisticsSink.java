package com.imooc.ecommerce.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author lichaochao
 * @data 2022/6/16 3:37 PM
 * 自定义物流信息接收器（Sink）
 **/
public abstract class LogisticsSink {

    /*** 输入信道名称*/
    private static final String INPUT = "logisticsInput";


    /**
     * <h2>物流 Sink -> logisticsInput</h2>
     */
    @Input(LogisticsSink.INPUT)
    abstract SubscribableChannel logisticsInput();
}
