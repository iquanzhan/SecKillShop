package com.chengxiaoxiao.seckillshop.controller;

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
    public String index()
    {
        return "index";
    }
}
