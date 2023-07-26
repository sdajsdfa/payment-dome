package com.yhgc.api.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {
	/**
	 * 创建并配置Cookie
	 * @param name  cookie的name
	 * @param value  cookie的value
	 * @param time  有效期，秒
	 * @param path  有效范围
	 * @return
	 */
	public static Cookie createCookie(String name, String value, int time, String path){
		Cookie cookie = new Cookie(name, value);
		//Cookie的一些其他设置，可以设置最大有效期，可以设置有效范围(在哪里能够获取到该Cookie信息)
		//设置有效期
		cookie.setMaxAge(time);
		//设置有效范围
		cookie.setPath(path);//只能在CookieDemo02这个Servlet中获取该Cookie的信息
		return cookie;
	}
	public static String getCookieValue(HttpServletRequest request, String cookieName){
		Cookie[] cookies = request.getCookies();
		String value = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookieName.equals(cookie.getName())) {
					//确实是咱们要获取的那个名为username的cookie对象
					value = cookie.getValue();
				}
			}
		}
		return value;
	}
}
