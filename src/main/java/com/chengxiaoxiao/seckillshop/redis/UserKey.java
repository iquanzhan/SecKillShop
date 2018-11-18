package com.chengxiaoxiao.seckillshop.redis;

/**
 * @author XiaoXiao
 * @version $Rev$
 */
public class UserKey extends BasePrefix {
    public static UserKey getUserById = new UserKey("id");

    public UserKey(String prefix) {
        super(prefix);
    }

}
