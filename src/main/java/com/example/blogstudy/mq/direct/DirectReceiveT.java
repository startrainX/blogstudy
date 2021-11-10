package com.example.blogstudy.mq.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zsp
 * @version 1.0
 * @date 2021/11/10 11:27
 * @description: @RabbitListener可以放在类上也可以放在方法上
 */
@Component
@RabbitListener(queues = "mall.order.cancel")
public class DirectReceiveT {

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("DirectReceiverT消费者收到消息  : " + testMessage.toString());
    }
}
