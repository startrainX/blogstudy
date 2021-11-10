package com.example.blogstudy.mq.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zsp
 * @version 1.0
 * @date 2021/11/10 16:33
 * @description: 消费者手动确认
 */
@Configuration
public class MessageListenerConfig {

    @Autowired
    private CachingConnectionFactory connectionFactory;
    @Autowired
    private MyAckReceiver myAckReceiver;

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(1);
        // RabbitMQ默认是自动确认，改为手动
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        // 设置一个队列
        container.setQueueNames("ackQueue", "fanout.A");
        // 如果同时设置多个如下： 前提是队列都是必须已经创建存在的
        // container.setQueueNames("TestDirectQueue","TestDirectQueue2","TestDirectQueue3");


        // 另一种设置队列的方法,如果使用这种情况,那么要设置多个,就使用addQueues
        // container.setQueues(new Queue("TestDirectQueue",true));
        // container.addQueues(new Queue("TestDirectQueue2",true));
        // container.addQueues(new Queue("TestDirectQueue3",true));
        container.setMessageListener(myAckReceiver);
        return container;
    }
}
