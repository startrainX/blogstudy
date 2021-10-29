package com.example.blogstudy.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zsp
 * @version 1.0
 * @date 2021/8/13 10:29
 * @description:
 */
@Data
public class UserEntity implements Serializable {
    private Long id;
    private String guid;
    private String name;
    private String age;
    private Date createTime;
}
