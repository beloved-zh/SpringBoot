package com.zh.config;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocaleResolver implements LocaleResolver {

    //解析请求
    @Override
    public Locale resolveLocale(HttpServletRequest request) {

        //获取请求中的语言参数
        String l = request.getParameter("l");

        Locale locale = Locale.getDefault();//如果没有就使用默认的

        //如果请求的链接携带了国际化参数
        if(!StringUtils.isEmpty(l)){//如果不为空 spring中的方法

            //zh_CN  拆分语言参数
            String[] s = l.split("_");
            //国家，地区
            locale = new Locale(s[0],s[1]);

        }

        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
