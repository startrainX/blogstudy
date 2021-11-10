package com.example.blogstudy.mq.fanout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zsp
 * @version 1.0
 * @date 2021/11/10 14:29
 * @description: 扇形交换机，三个队列会同时收到生产者发送的消息，消费者也都会消费
 */
@Configuration
public class FanoutRabbitMqConfig {

    @Bean
    public Queue fanout_a() {
        return new Queue("fanout.A");
    }

    @Bean
    public Queue fanout_b() {
        return new Queue("fanout.B");
    }

    @Bean
    public Queue fanout_c() {
        return new Queue("fanout.C");
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding bindingExchangeA() {
        return BindingBuilder.bind(fanout_a()).to(fanoutExchange());
    }

    @Bean
    Binding bindingExchangeB() {
        return BindingBuilder.bind(fanout_b()).to(fanoutExchange());
    }

    @Bean
    Binding bindingExchangeC() {
        return BindingBuilder.bind(fanout_c()).to(fanoutExchange());
    }
}
