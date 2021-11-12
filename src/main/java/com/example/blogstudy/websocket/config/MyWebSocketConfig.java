package com.example.blogstudy.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author zsp
 * @version 1.0
 * @date 2021/11/11 10:42
 * @description: EnableWebSocketMessageBroker-注解开启STOMP协议来传输基于代理的消息，此时控制器支持使用@MessageMapping
 */
@Configuration
@EnableWebSocketMessageBroker
public class MyWebSocketConfig implements WebSocketMessageBrokerConfigurer {

    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // topic用来广播，user用来点对点
        registry.enableSimpleBroker("/topic", "/user");
    }

    /**
     * 开放节点
     * 注册两个STOMP的节点，分别用于广播和点对点
     *
     * @param registry
     */
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 广播节点
        registry.addEndpoint("/publicServer").setAllowedOriginPatterns("*").withSockJS();
        // 点对点节点
        registry.addEndpoint("/privateServer").setAllowedOriginPatterns("*").withSockJS();
    }
}
