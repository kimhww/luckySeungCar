package com.luckyRepair.seungme.luckySeungCar.common.interceptor;

import com.luckyRepair.seungme.luckySeungCar.common.LuckySeungCarConstant;
import com.luckyRepair.seungme.luckySeungCar.main.login.domain.LoginUserInfoDomain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LoginUserInfoDomain userInfo = (LoginUserInfoDomain)request.getSession().getAttribute(LuckySeungCarConstant.LOGIN_USER);
        if(userInfo == null) {
            log.info(" not logged in user. {}-{}", request.getRemoteAddr(), request.getServletPath());
            request.getSession().setAttribute(LuckySeungCarConstant.REQUEST_URL, request.getServletPath());
            response.sendRedirect(request.getContextPath() +  "/login/loginForm");
            return false;
        }
        return true;
    }

}
