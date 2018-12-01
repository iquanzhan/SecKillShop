package com.chengxiaoxiao.seckillshop.domain;

/**
 * @ClassName MiaoshaOrder
 * @Author Cheng XiaoXiao
 * @GitHub https://github.com/iquanzhan
 * @Blog http://www.chengxiaoxiao.com
 * @Date 18-12-1 下午1:11
 * @descript 秒杀订单实体类
 */

public class MiaoshaOrder {
    private Long id;
    private Long userId;
    private Long orderId;
    private Long goodsId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
}
