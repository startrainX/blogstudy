package com.example.blogstudy.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author zsp
 * @version 1.0
 * @date 2021/10/31 16:14
 * @description: CusTomWebSocketHandler的config文件
 */
@Configuration
public class WebSocketConfig {

    /**
     * 注入一个ServerEndpointExporter，该bean会自动注册使用@ServerEndpoint注解申明的websocket endpoint
     *
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
