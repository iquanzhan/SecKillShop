package com.chengxiaoxiao.seckillshop.dao;

import com.chengxiaoxiao.seckillshop.domain.MiaoshaOrder;
import com.chengxiaoxiao.seckillshop.domain.OrderInfo;
import org.apache.ibatis.annotations.*;

/**
 * @ClassName OrderDao
 * @Author Cheng XiaoXiao
 * @GitHub https://github.com/iquanzhan
 * @Blog http://www.chengxiaoxiao.com
 * @Date 18-12-1 下午3:14
 * @descript
 */
@Mapper
public interface OrderDao {


    @Select("select * from miaosha_order where miaosha_order.goods_id=#{goodsId} and miaosha_order.user_id=#{userId}")
    public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(@Param("userId") long userId, @Param("goodsId") long goodsId);

    @Insert("insert into order_info(user_id,goods_id,delivery_addr_id,goods_name,goods_count,goods_price,order_channel,status,create_date,pay_date)  " +
            "values(#{userId},#{goodsId},#{deliveryAddrId},#{goodsName},#{goodsCount},#{goodsPrice},#{orderChannel},#{status},#{createDate},#{payDate})")
    @SelectKey(keyColumn = "id", keyProperty = "id", resultType = Long.class, before = false, statement = "select last_insert_id()")
    public Long insert(OrderInfo orderInfo);

    @Insert("insert into miaosha_order(user_id,order_id,goods_id) values(#{userId},#{orderId},#{goodsId})")
    void insertMiaoshaOrder(MiaoshaOrder miaoshaOrder);
}
