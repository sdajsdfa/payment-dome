package com.yhgc.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yhgc.api.entity.Appuser;
import com.yhgc.api.service.AppuserService;
import com.yhgc.api.util.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2022-12-21
 */
@Controller
public class AppuserController {

    @Resource
    private AppuserService appuserService;

    @Resource
    private HttpSession session;

    public static Map<String, Appuser> sessionUsermap = new HashMap<>();

    /**
     * 登录接口
     *
     * @param appuser
     * @return
     */
    @ApiOperation("登录接口")
    @PostMapping(value = "/login")
    @ResponseBody
    public R login(Appuser appuser, HttpServletResponse response, HttpServletRequest request) {
        QueryWrapper<Appuser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userName", appuser.getUserName());
        queryWrapper.eq("status", 0);
        Appuser user = appuserService.getOne(queryWrapper);
        if(user.getUserName().equals(appuser.getUserName())){
            if(user.getPassWord().equals(appuser.getPassWord())){
                session.setAttribute("appuser",user);
                Cookie c = new Cookie("JSESSIONID",session.getId());
                c.setMaxAge(60*30); //2周时间Cookie过期     单位秒
                c.setPath("/"); //表示任何请求路径都可以访问Cookie
                response.addCookie(c);
                sessionUsermap.put(session.getId(), user);
                return R.ok(user);
            }else {
                R.error("密码错误");
            }
        }else {
            R.error("用户名错误");
        }
        return R.ok(user);
    }

    @RequestMapping(value="/main")
    public String main(){
        return "main";
    }

    @RequestMapping(value="/AppIndex")
    public String AppIndex(){
        return "AppIndex";
    }

    @RequestMapping(value="/appLogin")
    public String appLogin(){
        return "login";
    }

    @RequestMapping(value="/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        request.getSession().invalidate();
        String sessionIdStr = "";
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if ("JSESSIONID".equals(cookie.getName())) {
                    sessionIdStr = cookie.getValue();
                    sessionUsermap.remove(sessionIdStr);//移除静态变量中的值
                }
            }
        }
        Cookie deleteNewCookie = new Cookie("JSESSIONID", null);
        deleteNewCookie.setMaxAge(0); // 删除该Cookie
        deleteNewCookie.setPath("/");
        response.addCookie(deleteNewCookie);
        return "/WEB-INF/view/login.jsp";
    }
}

