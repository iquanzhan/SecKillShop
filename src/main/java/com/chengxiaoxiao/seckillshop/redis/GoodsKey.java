package com.chengxiaoxiao.seckillshop.redis;

/**
 * @ClassName GoodsKey
 * @Author Cheng XiaoXiao
 * @GitHub https://github.com/iquanzhan
 * @Blog http://www.chengxiaoxiao.com
 * @Date 18-12-2 下午2:58
 * @descript TODO:
 */
public class GoodsKey extends BasePrefix {
    public GoodsKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static GoodsKey getGoodList = new GoodsKey(60, "goodList");
    public static GoodsKey getGoodDetails = new GoodsKey(60, "goodDetails");

}
