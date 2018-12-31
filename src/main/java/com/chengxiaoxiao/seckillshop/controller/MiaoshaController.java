package com.chengxiaoxiao.seckillshop.controller;

import com.chengxiaoxiao.seckillshop.domain.MiaoshaOrder;
import com.chengxiaoxiao.seckillshop.domain.MiaoshaUser;
import com.chengxiaoxiao.seckillshop.domain.OrderInfo;
import com.chengxiaoxiao.seckillshop.result.CodeMsg;
import com.chengxiaoxiao.seckillshop.result.Result;
import com.chengxiaoxiao.seckillshop.service.GoodsService;
import com.chengxiaoxiao.seckillshop.service.MiaoshaService;
import com.chengxiaoxiao.seckillshop.service.OrderService;
import com.chengxiaoxiao.seckillshop.vo.GoodsVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName MiaoshaController
 * @Author Cheng XiaoXiao
 * @GitHub https://github.com/iquanzhan
 * @Blog http://www.chengxiaoxiao.com
 * @Date 18-12-1 下午3:01
 * @descript
 */
@Controller
@RequestMapping("/miaosha")
public class MiaoshaController {

    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;
    @Autowired
    MiaoshaService miaoshaService;


    @RequestMapping("/do_miaosha")
    public String doMiaosha(Model model, MiaoshaUser user, @RequestParam("goodsId") long goodsId) {
        if (user == null) {
            return "login";
        }
        GoodsVo goodsVo = goodsService.getGoodsVoById(goodsId);
        if (goodsVo.getStockCount() <= 0) {
            model.addAttribute("errmsg", CodeMsg.MIAOSHA_OVER.getMsg());
            return "miaosha_fail";
        }

        //判断是否已经秒杀到了
        MiaoshaOrder miaoshaOrder = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if (miaoshaOrder != null) {
            model.addAttribute("errmsg", CodeMsg.MIAOSHA_REPEAT.getMsg());
            return "miaosha_fail";
        }

        //下单
        OrderInfo orderInfo = miaoshaService.miaosha(user, goodsVo);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goods", goodsVo);

        return "order_details";
    }
    @RequestMapping("/do_miaosha2")
    public Result doMiaosha2(Model model, MiaoshaUser user, @RequestParam("goodsId") long goodsId) {
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        GoodsVo goodsVo = goodsService.getGoodsVoById(goodsId);
        if (goodsVo.getStockCount() <= 0) {
            return Result.error(CodeMsg.MIAOSHA_OVER);
        }

        //判断是否已经秒杀到了
        MiaoshaOrder miaoshaOrder = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if (miaoshaOrder != null) {
            return Result.error(CodeMsg.MIAOSHA_REPEAT);
        }
        //下单
        OrderInfo orderInfo = miaoshaService.miaosha(user, goodsVo);

        return Result.success(orderInfo);
    }
}
