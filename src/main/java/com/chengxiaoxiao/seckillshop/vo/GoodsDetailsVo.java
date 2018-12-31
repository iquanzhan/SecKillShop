package com.chengxiaoxiao.seckillshop.vo;

import com.chengxiaoxiao.seckillshop.domain.MiaoshaUser;

import java.io.Serializable;

/**
 * @author XiaoXiao
 * @version $Rev$
 */
public class GoodsDetailsVo implements Serializable {

    private MiaoshaUser user;
    private GoodsVo goodsVo;
    private int miaoshaStatus;
    private int remainSeconds;

    public MiaoshaUser getUser() {
        return user;
    }

    public void setUser(MiaoshaUser user) {
        this.user = user;
    }

    public GoodsVo getGoodsVo() {
        return goodsVo;
    }

    public void setGoodsVo(GoodsVo goodsVo) {
        this.goodsVo = goodsVo;
    }

    public int getMiaoshaStatus() {
        return miaoshaStatus;
    }

    public void setMiaoshaStatus(int miaoshaStatus) {
        this.miaoshaStatus = miaoshaStatus;
    }

    public int getRemainSeconds() {
        return remainSeconds;
    }

    public void setRemainSeconds(int remainSeconds) {
        this.remainSeconds = remainSeconds;
    }
}
