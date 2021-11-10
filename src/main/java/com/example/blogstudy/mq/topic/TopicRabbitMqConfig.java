package com.example.blogstudy.mq.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zsp
 * @version 1.0
 * @date 2021/11/10 13:44
 * @description: 主题交换机
 */
@Configuration
public class TopicRabbitMqConfig {
    // 绑定键
    private static final String man = "topic.man";

    private static final String woman = "topic.woman";

    /**
     * 创建队列
     */
    @Bean
    public Queue firstQueue() {
        return new Queue(TopicRabbitMqConfig.man);
    }

    @Bean
    public Queue secondQueue() {
        return new Queue(TopicRabbitMqConfig.woman);
    }

    /**
     * 创建交换机
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    /**
     * 将firstQueue队列和topicExchange交换机通过topic.man绑定，只有携带该键的消息才会放入firstQueue
     *
     * @return
     */
    @Bean
    Binding bindingExchangeMessage() {
        return BindingBuilder.bind(firstQueue()).to(topicExchange()).with(man);
    }

    /**
     * 将secondQueue队列和topicExchange交换机使用通配符topic.#绑定，只要键是以topic.开头的消息都会放入secondQueue
     */
    @Bean
    Binding bindingExchange() {
        return BindingBuilder.bind(secondQueue()).to(topicExchange()).with("topic.#");
    }
}
