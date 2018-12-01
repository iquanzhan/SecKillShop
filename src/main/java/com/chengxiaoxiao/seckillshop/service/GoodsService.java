package com.chengxiaoxiao.seckillshop.service;

import com.chengxiaoxiao.seckillshop.dao.GoodsDao;
import com.chengxiaoxiao.seckillshop.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName GoodsService
 * @Author Cheng XiaoXiao
 * @GitHub https://github.com/iquanzhan
 * @Blog http://www.chengxiaoxiao.com
 * @Date 18-12-1 下午1:35
 * @descript GoodsService
 */
@Service
public class GoodsService {

    @Autowired
    GoodsDao goodsDao;


    public List<GoodsVo> listGoodsVo() {
        return goodsDao.listGoodsVo();
    }

    public GoodsVo getGoodsVoById(Long id) {
        return goodsDao.getGoodsVoById(id);
    }

}
