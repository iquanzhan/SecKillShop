package com.chengxiaoxiao.seckillshop.controller;

import com.chengxiaoxiao.seckillshop.domain.MiaoshaUser;
import com.chengxiaoxiao.seckillshop.redis.GoodsKey;
import com.chengxiaoxiao.seckillshop.redis.RedisService;
import com.chengxiaoxiao.seckillshop.result.Result;
import com.chengxiaoxiao.seckillshop.service.GoodsService;
import com.chengxiaoxiao.seckillshop.service.MiaoshaUserService;
import com.chengxiaoxiao.seckillshop.vo.GoodsDetailsVo;
import com.chengxiaoxiao.seckillshop.vo.GoodsVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.util.StringUtils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    //region 采用原生的网站开发
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

    //endregion

    @RequestMapping("/detail/{goodsId}")
    @ResponseBody
    public Result<GoodsDetailsVo> goodsDetail(Model model, MiaoshaUser user, @PathVariable("goodsId") long id) {
        GoodsVo goodsVo = goodsService.getGoodsVoById(id);
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

        GoodsDetailsVo goodsDetailsVo = new GoodsDetailsVo();
        goodsDetailsVo.setGoodsVo(goodsVo);
        goodsDetailsVo.setMiaoshaStatus(miaoshaStatus);
        goodsDetailsVo.setRemainSeconds(remainSeconds);
        goodsDetailsVo.setUser(user);

        return Result.success(goodsDetailsVo);
    }

}
