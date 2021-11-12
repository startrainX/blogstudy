package com.example.blogstudy.websocket;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zsp
 * @version 1.0
 * @date 2021/10/31 16:05
 * @description: websocket 允许服务端主动向客户端推送数据的一种基于tcp连接的全双工通信协议
 * 前后端交互的类实现消息的接收推送（自己发送给自己）
 * @ServerEndpoint(value = "/ws/first") 前端通过此uri和后端交互，建立连接
 * 此文件下的websocket示例和WebSocketConfig已经可以和templates下的index页面进行配合来简单的完成群发、单发了；
 * 其他几个是stomp的学习和整合，可以不用
 */
@Slf4j
@Component
@ServerEndpoint(value = "/ws/first")
public class CustomWebSocketHandler {
    /**
     * 记录当前在线连接数
     */
    private static AtomicInteger onlineCount = new AtomicInteger(0);

    /**
     * 存放所有在线的客户端
     */
    private static Map<String, Session> client = new ConcurrentHashMap<>();

    /**
     * 连接建立成功调用的方法
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        // 在线数加一
        onlineCount.incrementAndGet();
        client.put(session.getId(), session);
        log.info("有新连接加入：{}，当前在线人数为：{}", session.getId(), onlineCount.get());
    }

    /**
     * 连接关闭调用的方法
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        // 在线数减一
        onlineCount.decrementAndGet();
        client.remove(session.getId());
        log.info("有一连接关闭：{}，当前在线人数为：{}", session.getId(), onlineCount.get());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("服务端收到客户端【{}】的消息，：{}", session.getId(), message);
        // 发送消息调换方法
        this.sendMessageAll(message, session);
    }

    /**
     * 发生错误调用的方法
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("ws发生错误");
        error.printStackTrace();
    }

    /**
     * 服务端发送消息给客户端
     *
     * @param message
     * @param toSession
     */
    public void sendMessage(String message, Session toSession) {
        try {
            log.info("服务端给客户端【{}】发送消息{}", toSession.getId(), message);
            toSession.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.error("服务端发送消息给客户端失败：{}", e);
        }
    }

    /**
     * 群发消息
     *
     * @param message
     * @param fromSession
     */
    public void sendMessageAll(String message, Session fromSession) {
        for (Map.Entry<String, Session> sessionEntry : client.entrySet()) {
            Session toSession = sessionEntry.getValue();
            // 排除自己
            if (!fromSession.getId().equals(toSession.getId())) {
                log.info("服务端给客户端【{}】发送消息{}", toSession.getId(), message);
                toSession.getAsyncRemote().sendText(message);
            }
        }
    }

    /**
     * 给指定用户发送消息
     * {"message":"你好", "id":"3"}
     *
     * @param message
     * @param sessionId
     */
    public void sendMessageToUser(String message, Session sessionId) {
        Map<String, Object> map = JSONObject.parseObject(message, Map.class);
        if (!map.isEmpty()) {
            Session toSession = client.get(map.get("id"));
            log.info("客户端【{}】经由服务端转发给客户端【{}】消息{}", sessionId.getId(), toSession.getId(), message);
            this.sendMessage(message, toSession);
        }
    }
}
