package com.chengxiaoxiao.seckillshop.controller;

import com.chengxiaoxiao.seckillshop.domain.MiaoshaUser;
import com.chengxiaoxiao.seckillshop.result.CodeMsg;
import com.chengxiaoxiao.seckillshop.result.Result;
import com.chengxiaoxiao.seckillshop.service.MiaoshaService;
import com.chengxiaoxiao.seckillshop.util.MD5Util;
import com.chengxiaoxiao.seckillshop.util.ValidatorUtil;
import com.chengxiaoxiao.seckillshop.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    MiaoshaService miaoshaService;

    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<String> doLogin(UserVo userVo) {
        if (StringUtils.isEmpty(userVo.getMobile())) {
            return Result.error(CodeMsg.MOBILE_EMPTY);
        }
        if (!ValidatorUtil.isMobile(userVo.getMobile())) {
            return Result.error(CodeMsg.MOBILE_ERROR);
        }
        if (StringUtils.isEmpty(userVo.getPassword())) {
            return Result.error(CodeMsg.PASSWORD_EMPTY);
        }

        MiaoshaUser miaoshaUser = miaoshaService.getUserById(Long.valueOf(userVo.getMobile()));
        if (null == miaoshaUser) {
            return Result.error(CodeMsg.MOBILE_NOT_EXIST);
        }

        log.info("用户进入【用户登录】界面");

        String dbPass = miaoshaUser.getPassword();
        String saltDb = miaoshaUser.getSalt();

        String calcPass = MD5Util.formPassToDBPass(userVo.getPassword(), saltDb);

        if (calcPass.equals(dbPass)) {
            return Result.success("登录成功");
        } else {
            return Result.error(CodeMsg.MOBILE_PASSWORD_ERROR);
        }

    }
}
