package com.example.blogstudy.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.blogstudy.entity.UserEntity;
import com.example.blogstudy.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zsp
 * @version 1.0
 * @date 2021/8/13 10:28
 * @description:
 */
@Slf4j
@RequestMapping("/redis")
@RestController
public class RedisController {

    private static int ExpireTime = 60;   // redis中存储的过期时间60s
    @Autowired
    JedisPool jedisPool;
    @Resource
    private RedisUtil redisUtil;

    @GetMapping("set")
    public boolean redisset(String key, String value) {
        List<UserEntity> list = new ArrayList<>();
        UserEntity userEntity = new UserEntity();
        userEntity.setId(Long.valueOf(1));
        userEntity.setGuid(String.valueOf(1));
        userEntity.setName("张三");
        redisUtil.lSet("5", JSONArray.toJSONString(userEntity));
        Map<String, Object> map = new HashMap<>();
        map.put("1", userEntity);
        list.add(userEntity);
        userEntity = new UserEntity();
        userEntity.setId(Long.valueOf(1));
        userEntity.setGuid(String.valueOf(1));
        userEntity.setName("李四");
        list.add(userEntity);
        map.put("2", userEntity);
        redisUtil.hmset("map", map);
        redisUtil.lSet("5", JSONArray.toJSONString(userEntity));
        redisUtil.set(key, JSONArray.toJSONString(userEntity));
        redisUtil.lSet("2", JSONArray.toJSONString(list));

        return true;

//        return redisUtil.set(key, value);
    }

    @GetMapping("get")
    public String redisget(String key) {
        return redisUtil.lPop(key);
    }

    @GetMapping("redisGet")
    public String getKey(String key) {
        return redisUtil.get(key);
    }

    @GetMapping("expire")
    public boolean expire(String key) {
        return redisUtil.expire(key, ExpireTime);
    }
}
