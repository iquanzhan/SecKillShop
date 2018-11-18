package com.chengxiaoxiao.seckillshop.controller;

import com.chengxiaoxiao.seckillshop.domain.User;
import com.chengxiaoxiao.seckillshop.redis.RedisService;
import com.chengxiaoxiao.seckillshop.redis.UserKey;
import com.chengxiaoxiao.seckillshop.result.CodeMsg;
import com.chengxiaoxiao.seckillshop.result.Result;
import com.chengxiaoxiao.seckillshop.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author XiaoXiao
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    /**
     * 测试统一返回异常JSON格式字符串
     * @return
     */
    @RequestMapping("/index")
    @ResponseBody
    public Result index() {
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    /**
     * 使用thymeleaf
     * @param model
     * @return
     */
    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "Tom");
        return "index";
    }

    @RequestMapping("/db/index")
    @ResponseBody
    public Result getById() {

        return Result.success(userService.getById(1));
    }

    @RequestMapping("/db/insert")
    @ResponseBody
    public Result insert() {
        userService.insert(new User(1, "name"));
        return Result.success("成功");
    }

    @ResponseBody
    @RequestMapping("/redis")
    public Result<User> redis() {
        redisService.set(UserKey.getUserById, "key1", new User(1, "Tom"));

        User user = redisService.get(UserKey.getUserById, "key1", User.class);

        return Result.success(user);
    }

}
