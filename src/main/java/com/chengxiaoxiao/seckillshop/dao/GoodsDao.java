package com.chengxiaoxiao.seckillshop.dao;

import com.chengxiaoxiao.seckillshop.vo.GoodsVo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @ClassName GoodsDao
 * @Author Cheng XiaoXiao
 * @GitHub https://github.com/iquanzhan
 * @Blog http://www.chengxiaoxiao.com
 * @Date 18-12-1 下午1:24
 * @descript 商品表Dao
 */
@Mapper
public interface GoodsDao {

    @Select("select g.*,mg.miaosha_price,mg.stock_count,mg.start_date,mg.end_date from goods g LEFT JOIN miaosha_goods mg on g.id=mg.goods_id ")
    public List<GoodsVo> listGoodsVo();

    @Select("select g.*,mg.miaosha_price,mg.stock_count,mg.start_date,mg.end_date from goods g LEFT JOIN miaosha_goods mg on g.id=mg.goods_id  where g.id=#{id}")
    public GoodsVo getGoodsVoById(@Param("id") Long id);

    @Update("update miaosha_goods set stock_count = stock_count-1 where goods_id=#{id} and stock_count>1")
    void reduceStock(@Param("id") Long id);
}
