package com.imooc.ecommerce.fillter;

import com.imooc.ecommerce.fillter.AccessContext;
import com.lcc.constant.CommonConstant;
import com.lcc.utils.TokenParseUtil;
import com.lcc.vo.LoginUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lichaochao
 * @data 2022/6/11 10:27 AM
 * 用户身份统一登录拦截
 **/
@SuppressWarnings("all")
@Slf4j
@Component
public class LoginUserInfoInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        // 部分请求不需要带有身份信息，即白名单
        if (checkWhiteListUrl(request.getRequestURI())) {
            return true;
        }

        //尝试从http Header里面拿到token
        String token = request.getHeader(CommonConstant.JWT_USER_INFO_KEY);
        LoginUserInfo loginUserInfo = null;
        try {
            loginUserInfo = TokenParseUtil.parseUserInfoFromToken(token);
        } catch (Exception e) {
            log.info("parse login user info error [{}]", e.getMessage(), e);
        }

        //如果程序走到这里，说明header中没有token信息,当使用网关会对身份进行校验，故这里判断无意义
        if (null == loginUserInfo) {
            throw new RuntimeException("can not parse current login user");
        }
        log.info("set login user info: [{}]", request.getRequestURI());
        //设置当前请求上下文，把用户信息填充进去
        AccessContext.setLoginUserInfo(loginUserInfo);
        // false :不能执行下去，true :往下一步执行
        return false;
//        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 请求完全结束之后调用，常用于清除资源等工作
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        // 请求完毕后，清除资源防止线程重用导致 资源混乱 或  资源泄漏
        if (null != AccessContext.getLoginUserInfo()) {
            AccessContext.clearLoginUserInfo();
        }
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    /**
     * 检验是否是白名单接口
     * true :放行
     * 匹配其中是否包含关键字
     *
     * @param url
     * @return
     */
    private boolean checkWhiteListUrl(String url) {
        return StringUtils.containsAny(url,
                "springfox", "swagger");
    }
}
