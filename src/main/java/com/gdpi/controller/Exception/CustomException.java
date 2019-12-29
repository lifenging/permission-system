package com.gdpi.controller.Exception;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomException implements HandlerExceptionResolver {
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 打印异常
        ex.printStackTrace();  // 如果不写，控制台没有异常

        ModelAndView mv = new ModelAndView();
        // 设置返回的页面
        mv.setViewName("failer");
        // 保存错误信息
        mv.addObject("errorMsg","系统忙，请联系管理员，电话120");
        return mv;

    }
}
