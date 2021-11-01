package com.example.blogstudy.scheduler;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author zsp
 * @version 1.0
 * @date 2021/10/31 15:05
 * @description:
 */
//@Component
public class TestScheduler {

    @Scheduled(cron = "0/10 * * * * *")
    public void test() {
        System.out.println("每隔十秒打印一次");
    }
}
