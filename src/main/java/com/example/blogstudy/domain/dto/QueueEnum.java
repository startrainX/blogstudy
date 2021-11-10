package com.example.blogstudy.domain.dto;

import lombok.Getter;

/**
 * @author zsp
 * @version 1.0
 * @date 2021/11/10 10:58
 * @description: 消息队列枚举配置
 */
@Getter
public enum QueueEnum {

    /**
     * 消息通知队列
     */
    QUEUE_ORDER_CANCEL("mall.order.direct", "mall.order.cancel", "mall.order.cancel"),
    QUEUE_TEST_CANCEL("com.exchange.crazy", "com.test.crazy", "com.test.crazy");

    /**
     * 交换名称
     */
    private String exchange;
    /**
     * 队列名称
     */
    private String name;
    /**
     * 路由键
     */
    private String routeKey;


    QueueEnum(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }
}
