package com.example.blogstudy.mq.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zsp
 * @version 1.0
 * @date 2021/11/10 14:13
 * @description:
 */
@Component
//@RabbitListener(queues = {"topic.man"})
public class TopicReceive {

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("TopicReceive消费者topic.man收到消息  : " + testMessage.toString());
    }
}
