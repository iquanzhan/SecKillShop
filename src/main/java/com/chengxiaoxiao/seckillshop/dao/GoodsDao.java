package com.chengxiaoxiao.seckillshop.dao;

import com.chengxiaoxiao.seckillshop.domain.Goods;
import com.chengxiaoxiao.seckillshop.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

}
