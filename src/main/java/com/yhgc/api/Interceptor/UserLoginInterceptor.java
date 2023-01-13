package com.yhgc.api.Interceptor;

import com.yhgc.api.controller.AppuserController;
import com.yhgc.api.entity.Appuser;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLoginInterceptor implements HandlerInterceptor {

    @Resource
    private HttpSession session;

    /***
     * 在请求处理之前进行调用(Controller方法调用之前)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String sessionIdStr = "";
            //统一拦截（查询当前session是否存在user）(这里user会在每次登录成功后，写入session)
            Cookie[] cookies = request.getCookies();
            if (null != cookies) {
                for (Cookie cookie : cookies) {
                    if ("JSESSIONID".equals(cookie.getName())) {
                        sessionIdStr = cookie.getValue();
                    }
                }
            }
            Appuser loginUser = AppuserController.sessionUsermap.get(sessionIdStr);
            if (loginUser != null) {
                return true;
            }
            response.sendRedirect("/appLogin");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
        //如果设置为false时，被请求时，拦截器执行到此处将不会继续操作
        //如果设置为true时，请求将会继续执行后面的操作
    }
}