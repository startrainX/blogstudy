package com.example.blogstudy.common;

/**
 * @author zsp
 * @version 1.0
 * @date 2021/4/12 10:35
 * @description:
 */
public enum ExceptionEnum {
    /**
     * 遇事不决，量子力学；
     * 风格跳跃，虚拟世界；
     * 解释不通，穿越时空；
     * 不懂配色，赛博朋克；
     * 脑洞不够，平行宇宙；
     * 画面老土，追求复古；
     * 不清又不楚，致敬克苏鲁
     */

    BIZ_EXCEPTION(0, "业务异常"),

    DB_EXCEPTION(1, "数据库异常"),

    REDIS_EXCEPTION(2, "redis异常"),

    ES_EXCEPTION(3, "ES异常"),

    MQ_EXCEPTION(4, "消息队列异常"),

    SYS_EXCEPTION(5, "系统异常");

    int type;
    String description;

    ExceptionEnum(int type, String description) {
        this.type = type;
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
