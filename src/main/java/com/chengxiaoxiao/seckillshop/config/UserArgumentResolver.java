package com.chengxiaoxiao.seckillshop.config;

import com.chengxiaoxiao.seckillshop.domain.MiaoshaUser;
import com.chengxiaoxiao.seckillshop.service.MiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    MiaoshaUserService miaoshaUserService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> type = methodParameter.getParameterType();
        return type == MiaoshaUser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);

        String paramsToken = request.getParameter(MiaoshaUserService.LOGIN_COOKIE_TOKEN);
        String cookieToken = getCookieValue(request, MiaoshaUserService.LOGIN_COOKIE_TOKEN);
        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramsToken)) {
            return null;
        }
        String token = StringUtils.isEmpty(paramsToken) ? cookieToken : paramsToken;

        MiaoshaUser user = miaoshaUserService.getMiaoshaUserByToken(token, response);
        return user;
    }

    /**
     * get cookie by request
     *
     * @param request
     * @param loginCookieToken
     * @return
     */
    private String getCookieValue(HttpServletRequest request, String loginCookieToken) {
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals(loginCookieToken)) {
                return c.getValue();
            }
        }
        return null;
    }
}
