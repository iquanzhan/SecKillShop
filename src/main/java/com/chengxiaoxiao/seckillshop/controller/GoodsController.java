package com.chengxiaoxiao.seckillshop.controller;

import com.chengxiaoxiao.seckillshop.domain.MiaoshaUser;
import com.chengxiaoxiao.seckillshop.domain.User;
import com.chengxiaoxiao.seckillshop.service.GoodsService;
import com.chengxiaoxiao.seckillshop.service.MiaoshaUserService;
import com.chengxiaoxiao.seckillshop.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
@RequestMapping("/goods")
public class GoodsController {


    @Autowired
    MiaoshaUserService miaoshaService;

    @Autowired
    GoodsService goodsService;

    @RequestMapping("/to_list")
    public String toList(Model model, MiaoshaUser user) {
        model.addAttribute("user", user);
        model.addAttribute("listGoodsVo", goodsService.listGoodsVo());

        return "goodlist";
    }

    @RequestMapping("/details/{goodsId}")
    public String goodsDetails(Model model, MiaoshaUser user, @PathVariable("goodsId") long id) {
        model.addAttribute("user", user);

        GoodsVo goodsVo = goodsService.getGoodsVoById(id);
        model.addAttribute("goodVo", goodsVo);

        //判断秒杀状态
        Long startDate = goodsVo.getStartDate().getTime();
        Long endData = goodsVo.getEndDate().getTime();
        Long now = System.currentTimeMillis();

        int miaoshaStatus = 0;
        int remainSeconds = 0;
        if (now < startDate) {
            miaoshaStatus = 0;
            remainSeconds = (int) ((startDate - now) / 1000);
        } else if (now > endData) {
            miaoshaStatus = 2;
            remainSeconds = -1;
        } else {
            miaoshaStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("miaoshaStatus", miaoshaStatus);
        model.addAttribute("remainSeconds", remainSeconds);

        return "goods_details";
    }


}
