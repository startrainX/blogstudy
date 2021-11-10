package com.example.blogstudy.mq;

import com.example.blogstudy.domain.dto.QueueEnum;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author zsp
 * @version 1.0
 * @date 2021/11/10 11:18
 * @description:
 */
@RestController
public class RabbitMqSendMessageController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/directMessage")
    public String directMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "direct message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        //将消息携带绑定键值：mall.order.cancel 发送到交换机mall.order.direct
        rabbitTemplate.convertAndSend(QueueEnum.QUEUE_ORDER_CANCEL.getExchange(),
                QueueEnum.QUEUE_ORDER_CANCEL.getRouteKey(), map);
        return "ok";
    }

    @GetMapping("/directMessage2")
    public String directMessage2() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "sendMessage2, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        //将消息携带绑定键值：mall.order.cancel 发送到交换机mall.order.direct
        rabbitTemplate.convertAndSend(QueueEnum.QUEUE_TEST_CANCEL.getExchange(),
                QueueEnum.QUEUE_TEST_CANCEL.getRouteKey(), map);
        return "ok";
    }

    @GetMapping("/topicMessage")
    public String topicMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "topic.man message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        //将消息携带绑定键值：mall.order.cancel 发送到交换机mall.order.direct
        rabbitTemplate.convertAndSend("topicExchange",
                "topic.man", map);
        return "ok";
    }

    @GetMapping("/topicMessage2")
    public String topicMessage2() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "topic.woman message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        //将消息携带绑定键值：mall.order.cancel 发送到交换机mall.order.direct
        rabbitTemplate.convertAndSend("topicExchange",
                "topic.woman", map);
        return "ok";
    }

    @GetMapping("/fanoutMessage")
    public String fanoutMessage() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "fanoutMessage, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        //将消息携带绑定键值：mall.order.cancel 发送到交换机mall.order.direct
        rabbitTemplate.convertAndSend("fanoutExchange",
                null, map);
        return "ok";
    }
}
