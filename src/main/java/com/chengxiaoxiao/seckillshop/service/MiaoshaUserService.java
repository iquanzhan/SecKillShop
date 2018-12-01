package com.chengxiaoxiao.seckillshop.service;

import com.chengxiaoxiao.seckillshop.dao.MiaoshaUserDao;
import com.chengxiaoxiao.seckillshop.domain.MiaoshaUser;
import com.chengxiaoxiao.seckillshop.exception.GlobleException;
import com.chengxiaoxiao.seckillshop.redis.MiaoshaKey;
import com.chengxiaoxiao.seckillshop.redis.RedisService;
import com.chengxiaoxiao.seckillshop.result.CodeMsg;
import com.chengxiaoxiao.seckillshop.result.Result;
import com.chengxiaoxiao.seckillshop.util.MD5Util;
import com.chengxiaoxiao.seckillshop.util.UUIDUtil;
import com.chengxiaoxiao.seckillshop.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class MiaoshaUserService {

    public static final String LOGIN_COOKIE_TOKEN = "token";


    @Autowired
    MiaoshaUserDao miaoshaUserDao;

    @Autowired
    RedisService redisService;

    public MiaoshaUser getUserById(long id) {
        return miaoshaUserDao.getUserById(id);
    }

    public boolean login(HttpServletResponse response, UserVo userVo) {

        if (userVo == null) {
            throw new GlobleException(CodeMsg.ERROR);
        }
        MiaoshaUser miaoshaUser = getUserById(Long.valueOf(userVo.getMobile()));
        if (null == miaoshaUser) {
            throw new GlobleException(CodeMsg.MOBILE_NOT_EXIST);
        }

        String dbPass = miaoshaUser.getPassword();
        String saltDb = miaoshaUser.getSalt();
        // md5 password 2 times
        String calcPass = MD5Util.formPassToDBPass(userVo.getPassword(), saltDb);

        if (!calcPass.equals(dbPass)) {
            throw new GlobleException(CodeMsg.MOBILE_PASSWORD_ERROR);
        }
        //不用每次都生成新的uuid
        String token = UUIDUtil.uuid();
        addCookie(miaoshaUser, token, response);

        return false;
    }

    public MiaoshaUser getMiaoshaUserByToken(String token, HttpServletResponse response) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        MiaoshaUser user = redisService.get(MiaoshaKey.token, token, MiaoshaUser.class);
        //when user login and use the website,extend to the cookie datetime
        if (user != null) {
            addCookie(user, token, response);
        }
        return user;
    }

    public void addCookie(MiaoshaUser miaoshaUser, String token, HttpServletResponse response) {

        redisService.set(MiaoshaKey.token, token, miaoshaUser);

        Cookie cookie = new Cookie(LOGIN_COOKIE_TOKEN, token);
        cookie.setMaxAge(MiaoshaKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
