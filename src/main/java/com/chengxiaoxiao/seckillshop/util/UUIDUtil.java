package com.chengxiaoxiao.seckillshop.util;

import java.util.UUID;

/**
 * UUID生成类
 */
public class UUIDUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
