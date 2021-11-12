package com.example.blogstudy.websocket.controller;

import com.example.blogstudy.websocket.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zsp
 * @version 1.0
 * @date 2021/11/11 10:55
 * @description:
 */
@RestController
public class TestController {

    @Autowired
    private SimpMessagingTemplate template;

    @PostMapping("/pushToAll")
    public void subscribe(@RequestBody Message message) {
        template.convertAndSend("/topic/all", message.getContent());
    }

    /**
     * 使用MessageMapping注解，可以在前端直接调用此接口
     *
     * @param message
     */
    @MessageMapping("/alone")
    @PostMapping("/pushToUser")
    public void queue(@RequestBody Message message) {
        System.out.println("发送消息给指定用户接口");
        template.convertAndSendToUser(message.getTo(), "/message", message.getContent());
    }
}
