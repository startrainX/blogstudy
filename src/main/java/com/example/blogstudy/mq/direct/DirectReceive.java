package com.example.blogstudy.mq.direct;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zsp
 * @version 1.0
 * @date 2021/11/10 11:27
 * @description: 绑定了两个路由键的队列，两者的数据都会接收，
 * 其中“mall.order.cancel”还有一个消费者，因此这个队列中的数据是被轮询接收的
 */
@Component
public class DirectReceive {

    @RabbitListener(queues = {"mall.order.cancel", "com.test.crazy"})
    public void process(Map testMessage) {
        System.out.println("DirectReceiver消费者收到消息  : " + testMessage.toString());
    }

    @RabbitListener(queues = {"topic.man"})
    public void topic(Map testMessage) {
        System.out.println("TopicReceive消费者topic.man收到消息  : " + testMessage.toString());
    }
}
