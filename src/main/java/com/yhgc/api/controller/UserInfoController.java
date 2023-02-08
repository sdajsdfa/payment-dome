package com.yhgc.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yhgc.api.entity.UserInfo;
import com.yhgc.api.service.UserInfoService;
import com.yhgc.api.util.R;
import com.yhgc.api.util.TokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-20
 */
@Api(tags = "用户信息")
@RestController
@RequestMapping("/userinfo")
public class UserInfoController {

    @Resource
    private UserInfoService userinfoService;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 登录接口
     *
     * @param account
     * @param password
     * @return
     */
    @ApiOperation("登录接口")
    @GetMapping   (value = "/login")
    public R login(String account, String password,String code,HttpSession httpSession,HttpServletResponse response) {
        Map<String,Object> map = new HashMap<>();
        if (account == null || password == null || code == null) {
            return R.error("输入的用户名或密码不能为空");
        }
        if(httpSession.getAttribute("code")==null){
            return R.error("验证码不存在");
        }
        if(!(httpSession.getAttribute("code").equals(code))){
            return R.error("验证码错误");
        }
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account);
        queryWrapper.eq("status", 0);
        UserInfo userinfo = userinfoService.getOne(queryWrapper);
        String md5Password = getMd5(password, account);
        if (!userinfo.getPassword().equals(md5Password)) {
            return R.error("密码不正确");
        }
        String token = TokenUtils.getToken(userinfo);
        map.put("id",userinfo.getId());
        map.put("account",userinfo.getAccount());
        map.put("token",token);
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        response.addCookie(cookie);
        httpSession.setAttribute(token,userinfo);
//      redisTemplate.opsForValue().set(token, userinfo);
        return R.ok(map);
    }

    //md5加密规则
    private String getMd5(String password, String account) {
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((account + password + account).getBytes()).toUpperCase();
        }
        return password;
    }

    /**
     * 注册接口(添加人员)
     * @param userinfo
     * @return
     * @throws Exception
     */
    @ApiOperation("添加人员")
    @PostMapping(value = "/addUser")
    public R addUser(@RequestBody UserInfo userinfo){
        String account = userinfo.getAccount();
        String password = userinfo.getPassword();
        String idCard = userinfo.getIdCard();
        String phoneNum = userinfo.getPhoneNum();
        if (account==null || password==null || idCard == null || phoneNum == null){
            return R.error("输入的用户名或密码或身份证或手机号不能为空");
        }
        QueryWrapper<UserInfo> queryAccount = new QueryWrapper<>();
        queryAccount.eq("account",account);
        UserInfo qa =  userinfoService.getOne(queryAccount);
        if (qa != null){
            return R.error("用户名已经被注册");
        }
        QueryWrapper<UserInfo> queryIdCard = new QueryWrapper<>();
        queryIdCard.eq("idCard",idCard);
        UserInfo qc =  userinfoService.getOne(queryIdCard);
        if (qc != null){
            return R.error("身份证已经被注册");
        }
        QueryWrapper<UserInfo> queryPhoneNum = new QueryWrapper<>();
        queryPhoneNum.eq("phoneNum",phoneNum);
        UserInfo qp =  userinfoService.getOne(queryPhoneNum);
        if (qp != null){
            return R.error("手机号已经被注册");
        }
//      String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = getMd5(password, account);
        userinfo.setPassword(md5Password);
        userinfo.setStatus(0);
        userinfo.setCreateTime(new Date());
        Boolean b = userinfoService.save(userinfo);
        if (!b) {
            return R.error("添加失败");
        }
        return R.ok(userinfo);
    }

    /**
     * 修改人员
     * @param userinfo
     * @return
     */
    @ApiOperation("修改人员")
    @PostMapping(value = "/updateUser")
    public R updateUser(@RequestBody UserInfo userinfo) {
        userinfo.setCreateTime(new Date());
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userinfo.getId());
        Boolean u = userinfoService.update(userinfo,queryWrapper);
        if (!u) {
            return R.error("修改失败");
        }
        return R.ok();
    }

    /**
     * 暂停人员
     * @param userinfo
     * @return
     */
    @ApiOperation("暂停人员")
    @PostMapping(value = "/pauseUser")
    public R pauseUser(@RequestBody UserInfo userinfo) {
        userinfo.setCreateTime(new Date());
        userinfo.setStatus(1);
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userinfo.getId());
        Boolean u = userinfoService.update(userinfo,queryWrapper);
        if (!u) {
            return R.error("暂停人员失败");
        }
        return R.ok(userinfo);
    }

    /**
     * 删除人员
     * @param userinfo
     * @return
     */
    @ApiOperation("删除人员")
    @PostMapping(value = "/invalidUser")
    public R invalidUser(@RequestBody UserInfo userinfo) {
        userinfo.setStatus(2);
        //将实体对象进行包装，包装为操作条件
        Boolean ui =  userinfoService.updateById(userinfo);
        if (!ui) {
            return R.error("删除人员失败");
        }
        return R.ok(userinfo);
    }

    /**
     * 查询人员（按单位）
     * @param unitId
     * @return
     */
    @ApiOperation("查询人员（按单位）")
    @GetMapping(value = "/queryUsers")
    public R queryUsers(Integer unitId){
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("unitId", unitId);
        List<UserInfo> userinfo = userinfoService.list(queryWrapper);
        return R.ok(userinfo);
    }

    /**
     * 查询人员（按部门）
     * @param unitId
     * @param dptId
     * @return
     */
    @ApiOperation("查询人员（按部门）")
    @GetMapping(value = "/queryUsersByDpt")
    public R queryUsersByDpt(Integer unitId,Integer dptId){
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("unitId", unitId);
        queryWrapper.eq("dptId", dptId);
        List<UserInfo> userinfo = userinfoService.list(queryWrapper);
        return R.ok(userinfo);
    }

    /**
     * 查询人员（按状态）
     * @param unitId
     * @param status
     * @return
     */
    @ApiOperation("查询人员（按状态）")
    @GetMapping(value = "/queryUsersInByStatus")
    public R queryUsersInByStatus(Integer unitId,Integer status){
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("unitId", unitId);
        queryWrapper.eq("status", status);
        List<UserInfo> userinfo = userinfoService.list(queryWrapper);
        return R.ok(userinfo);
    }

    /**
     * 生成图片验证码
     * @param request
     * @param response
     * @param httpSession
     * @throws IOException
     * @throws InterruptedException
     */
    @ApiOperation("生成图片验证码")
    @GetMapping("/getCodeImg")
    public void getCodeImage(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) throws IOException {
        BufferedImage image=new BufferedImage(80, 32, BufferedImage.TYPE_3BYTE_BGR);
        //编辑图像
        //获取绘图对象
        Graphics g=image.getGraphics();
        g.setColor(new Color(239, 239, 239));
        g.fillRect(0,0,80,32);
        //设置字体颜色
        g.setColor(new Color(49, 49, 49));
        //设置字体
        g.setFont(new Font("SimSong",Font.ITALIC,20));
        //绘制字符串；
        String text="";
        for(int i=0;i<4;i++) {
            text +=(int) (Math.random()*10);
        }
        //字符串输出内容，水平起始坐标，垂直起始坐标。
        g.drawString(text, 17, 24);
        //画线条
        for (int i = 0; i < 10; i++) {
            g.setColor(new Color((int) (Math.random()*255), (int) (Math.random()*255), (int) (Math.random()*255)));
            g.drawLine((int) (Math.random()*50),(int) (Math.random()*30),(int) (Math.random()*80),(int) (Math.random()*80));
        }
        //设置session
        httpSession.setAttribute("code",text);
        request.getSession().setMaxInactiveInterval(60);
        //输出图像
        //ImageIO.write(image, "png", new FileOutputStream("C:/Users/H/Desktop/"+tet+".png"));
        ImageIO.write(image, "png",response.getOutputStream());
        g.dispose();
    }

    //获取保存在session中的验证码
    @ApiOperation("获取图片验证码")
    @GetMapping("/getCode")
    public String getCode(HttpSession httpSession){
        return (String) httpSession.getAttribute("code");
    }

    /**
     *
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @ApiOperation("修改用户密码")
    @PostMapping(value = "/updatePwd")
    public R updatePwd(Long id,String oldPassword,String newPassword ){
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        queryWrapper.eq("status",0);
        UserInfo userinfo=userinfoService.getOne(queryWrapper);
        String md5Password = getMd5(oldPassword, userinfo.getAccount());
        if (!userinfo.getPassword().equals(md5Password)) {
            return R.error("密码不正确");
        }
        QueryWrapper<UserInfo> query= new QueryWrapper<>();
        query.eq("id",userinfo.getId());
        UserInfo user = new UserInfo();
        user.setPassword(newPassword);
        Boolean ui = userinfoService.update(user,queryWrapper);
        if (!ui) {
            return R.error("修改密码失败");
        }
        return R.ok();
    }
}

