package com.chengxiaoxiao.seckillshop.redis;

/**
 * @author XiaoXiao
 * @version $Rev$
 */
public class BasePrefix implements KeyPrefix {
    private String prefix;
    private int expireSeconds;

    public BasePrefix(int expireSeconds, String prefix) {
        this.prefix = prefix;
        this.expireSeconds = expireSeconds;
    }

    public BasePrefix(String prefix) {
        //默认0，代表永不过期
        this(0, prefix);
    }

    @Override
    public int expireSeconds() {
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className + ":" + prefix;
    }
}
