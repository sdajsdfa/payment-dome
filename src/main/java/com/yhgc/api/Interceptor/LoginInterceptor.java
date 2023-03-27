package com.yhgc.api.Interceptor;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.yhgc.api.entity.UserInfo;
import com.yhgc.api.service.PassToken;
import com.yhgc.api.service.UserinfoLoginToken;
import com.yhgc.api.service.UserInfoService;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class LoginInterceptor implements HandlerInterceptor {

      @Resource
      private UserInfoService userinfoService;

      @Override
      public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
                String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
                // 如果不是映射到方法直接通过
                if(!(object instanceof HandlerMethod)){
                        return true;
                }
                HandlerMethod handlerMethod=(HandlerMethod)object;
                Method method=handlerMethod.getMethod();
                //检查是否有passtoken注释，有则跳过认证
                if (method.isAnnotationPresent(PassToken.class)) {
                        PassToken passToken = method.getAnnotation(PassToken.class);
                        if (passToken.required()) {
                                return true;
                        }
                }
                //检查有没有需要用户权限的注解
                if (method.isAnnotationPresent(UserinfoLoginToken.class)) {
                        UserinfoLoginToken userLoginToken = method.getAnnotation(UserinfoLoginToken.class);
                        if (userLoginToken.required()) {
                                // 执行认证
                                if (token == null) {
                                        throw new RuntimeException("无token，请重新登录");
                                }
                                // 获取 token 中的 user id
                                String id;
                                try {
                                        id = JWT.decode(token).getAudience().get(0);
                                } catch (JWTDecodeException j) {
                                        throw new RuntimeException("401");
                                }
                                UserInfo userinfo = userinfoService.getById(id);
//                                UserInfo session = (UserInfo) httpServletRequest.getSession(true).getAttribute(token);
//                                if(!session.equals(userinfo)){
//                                    throw new RuntimeException("token失效");
//                                }
                                if (userinfo == null) {
                                        throw new RuntimeException("用户不存在，请重新登录");
                                }
                                // 验证 token
                                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(userinfo.getPassWord())).build();
                                try {
                                        jwtVerifier.verify(token);
                                } catch (JWTVerificationException e) {
                                        throw new RuntimeException("401");
                                }
                                return true;
                        }
                }
                return true;
           }

        }
 