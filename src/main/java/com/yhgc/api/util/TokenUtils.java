package com.yhgc.api.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.yhgc.api.entity.UserInfo;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author ：Mr.ZJW
 * @date ：Created 2022/2/28 10:24
 * @description：
 */
public class TokenUtils {

    public static String getTokenUserInfoId() {
        String token = getRequest().getHeader("token");// 从 http 请求头中取出 token
        String UserInfoId = JWT.decode(token).getAudience().get(0);
        return UserInfoId;
    }

    /**
     * 获取request
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }

    /**
     * 生成token值
     * @param userinfo
     * @return
     */
    public static String getToken(UserInfo userinfo) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60* 60 * 1000;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";

        token = JWT.create().withAudience(String.valueOf(userinfo.getId())).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(userinfo.getPassword()));
        return token;
    }

}
