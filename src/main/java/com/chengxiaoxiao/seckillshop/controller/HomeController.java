package com.chengxiaoxiao.seckillshop.controller;

import com.chengxiaoxiao.seckillshop.result.Result;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author XiaoXiao
 * @version $Rev$
 */
@Controller
@RequestMapping("/home")
public class HomeController
{
    @RequestMapping("/index")
    @ResponseBody
    public Result index()
    {
        return new Result(200, "成功", "hello");
    }


}
