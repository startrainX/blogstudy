package com.example.blogstudy.mq.config;


import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zsp
 * @version 1.0
 * @date 2021/11/10 14:45
 * @description: 生产者推送消息的消息确认调用回调函数
 * rabbitmq3.8.19版本以上，推送成功的消息不会走confirm回调了，
 * 只有交换机不存在会回调confirm，队列不存在会回调returnedMessage。
 */
@Configuration
public class RabbitConfig {

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        // 设置开启Mandatory才能触发回调函数，无论推送结果如何，都会强制调用回调函数
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("ConfirmCallback:     " + "相关数据：" + correlationData);
                System.out.println("ConfirmCallback:     " + "确认情况：" + ack);
                System.out.println("ConfirmCallback:     " + "原因：" + cause);
            }
        });

        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returnedMessage) {
                System.out.println("ReturnCallback:     " + "消息：" + returnedMessage.getMessage());
                System.out.println("ReturnCallback:     " + "回应码：" + returnedMessage.getReplyCode());
                System.out.println("ReturnCallback:     " + "回应信息：" + returnedMessage.getReplyText());
                System.out.println("ReturnCallback:     " + "交换机：" + returnedMessage.getExchange());
                System.out.println("ReturnCallback:     " + "路由键：" + returnedMessage.getRoutingKey());
            }
        });
        return rabbitTemplate;
    }
}
