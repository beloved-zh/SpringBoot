package com.zh.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截器
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        Object username = session.getAttribute("username");
        System.out.println("进入"+username);
        if (username == null){//没有登录
            request.setAttribute("msg","没有权限，请登录");
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }

        return true;
    }

}
