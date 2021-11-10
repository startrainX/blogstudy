package com.example.blogstudy.mq.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zsp
 * @version 1.0
 * @date 2021/11/10 14:35
 * @description:
 */
@Component
@RabbitListener(queues = "fanout.C")
public class FanoutReceiveC {

    @RabbitHandler
    public void processor(Map map) {
        System.out.println("FanoutReceiveC收到消息：" + map.toString());
    }
}
