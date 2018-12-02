package com.chengxiaoxiao.seckillshop.controller;

import com.chengxiaoxiao.seckillshop.domain.MiaoshaUser;
import com.chengxiaoxiao.seckillshop.domain.User;
import com.chengxiaoxiao.seckillshop.redis.GoodsKey;
import com.chengxiaoxiao.seckillshop.redis.RedisService;
import com.chengxiaoxiao.seckillshop.service.GoodsService;
import com.chengxiaoxiao.seckillshop.service.MiaoshaUserService;
import com.chengxiaoxiao.seckillshop.vo.GoodsVo;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.context.webflux.SpringWebFluxContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {


    @Autowired
    MiaoshaUserService miaoshaService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    RedisService redisService;

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;
    @Autowired
    ApplicationContext applicationContext;


    @RequestMapping("/to_list")
    @ResponseBody
    public String toList(Model model, MiaoshaUser user, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("user", user);
        //取缓存
        String html = redisService.get(GoodsKey.getGoodList, "", String.class);
        if (!StringUtils.isEmpty(html)) {
            return html;
        }
        List<GoodsVo> listGoodsVo = goodsService.listGoodsVo();
        model.addAttribute("listGoodsVo", listGoodsVo);

        WebContext ctx = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goodlist", ctx);
        if (!StringUtils.isEmpty(html)) {
            redisService.set(GoodsKey.getGoodList, "", html);
        }
        return html;
    }

    @RequestMapping("/details/{goodsId}")
    @ResponseBody
    public String goodsDetails(Model model, MiaoshaUser user, @PathVariable("goodsId") long id, HttpServletResponse response, HttpServletRequest request) {
        model.addAttribute("user", user);

        String html = redisService.get(GoodsKey.getGoodDetails, "" + id, String.class);
        if (!StringUtils.isEmpty(html)) {
            return html;
        }
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

        //加入到缓存中
        WebContext ctx = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goods_details", ctx);

        if (!StringUtils.isEmpty(html)) {
            redisService.set(GoodsKey.getGoodDetails, "" + id, html);
        }
        return html;
    }


}
