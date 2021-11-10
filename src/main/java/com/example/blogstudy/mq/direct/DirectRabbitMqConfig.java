package com.example.blogstudy.mq.direct;


import com.example.blogstudy.domain.dto.QueueEnum;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息队列相关配置
 * Created by macro on 2018/9/14.
 */
@Configuration
public class DirectRabbitMqConfig {

    /**
     * 会自动创建这个交换机
     * 订单消息实际消费队列所绑定的交换机
     */
    @Bean
    DirectExchange orderDirect() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_ORDER_CANCEL.getExchange())
                .durable(true)
                .build();
    }

    @Bean
    DirectExchange crazyDirect() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_TEST_CANCEL.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 创建队列
     * 订单实际消费队列
     */
    @Bean
    public Queue orderQueue() {
        return new Queue(QueueEnum.QUEUE_ORDER_CANCEL.getName());
    }

    @Bean
    public Queue crazyQueue() {
        return new Queue(QueueEnum.QUEUE_TEST_CANCEL.getName());
    }

    /**
     * 将订单队列绑定到交换机
     */
    @Bean
    Binding orderBinding(DirectExchange orderDirect, Queue orderQueue) {
        return BindingBuilder
                .bind(orderQueue)
                .to(orderDirect)
                .with(QueueEnum.QUEUE_ORDER_CANCEL.getRouteKey());
    }

    @Bean
    Binding crazyBinding(DirectExchange crazyDirect, Queue crazyQueue) {
        return BindingBuilder
                .bind(crazyQueue)
                .to(crazyDirect)
                .with(QueueEnum.QUEUE_TEST_CANCEL.getRouteKey());
    }

    /**
     * 以下交换机和队列为测试消息确认使用的，虽然使用一次过后应该就会创建交换机和队列了
     */
    @Bean
    DirectExchange ackExchange() {
        return new DirectExchange("ackExchange");
    }

    @Bean
    Queue ackQueue() {
        return new Queue("ackQueue");
    }

    @Bean
    Binding bindingACK() {
        return BindingBuilder.bind(ackQueue()).to(ackExchange()).with("ack");
    }

}
