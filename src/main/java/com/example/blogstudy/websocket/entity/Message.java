package com.example.blogstudy.websocket.entity;

import lombok.Data;

/**
 * @author zsp
 * @version 1.0
 * @date 2021/11/11 10:50
 * @description: 推送消息实体类
 */
@Data
public class Message {
    /**
     * 消息编码
     */
    private String code;

    /**
     * 来自（保证唯一）
     */
    private String form;

    /**
     * 去自（保证唯一）
     */
    private String to;

    /**
     * 内容
     */
    private String content;
}
