package com.chengxiaoxiao.seckillshop.redis;

public class MiaoshaKey extends BasePrefix {

    private static final int TOKEN_EXPIRE = 3600 * 24 * 2;

    public MiaoshaKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static MiaoshaKey token = new MiaoshaKey(TOKEN_EXPIRE, "logintk");
}
