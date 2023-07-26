package com.yhgc.api.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.yhgc.api.util.CookieUtil;
import com.yhgc.api.vo.UserInfoVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.config.IdentifyCode;
import com.yhgc.api.dto.UserInfo2Dto;
import com.yhgc.api.entity.*;
import com.yhgc.api.entity.UserInfo2Vo;
import com.yhgc.api.service.UserinfoLoginToken;
import com.yhgc.api.util.R;
import com.yhgc.api.util.TokenUtils;
import com.yhgc.api.vo.UserInfo3Vo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-26
 */
@Api(tags = "用户信息2")
@RestController
@RequestMapping("/userInfo2")
@Transactional
public class UserInfo2Controller extends BaseController {

    public static Map<String, UserInfo2> sessionUsermap = new HashMap<>();

    /**
     * 登录接口
     *
     * @param user
     * @return
     */
    @ApiOperation("登录接口")
    @PostMapping(value = "/login")
    public R login(UserInfo2 user, HttpServletRequest request, HttpServletResponse response) {
        System.out.println(user+"============="+redisTemplate.opsForValue().get("code"));
        Map<String,Object> map = new HashMap<>();
//        String redisCode = (String) redisUtil.hget((String) redisUtil.get("key"),"code");
        String redisCode = (String) redisTemplate.opsForValue().get("code");
        if (StringUtils.isEmpty(user.getAccount()) || StringUtils.isEmpty(user.getPassword())||StringUtils.isEmpty(redisCode)) {
            return R.error("输入的用户名或密码或验证码不能为空");
        }
        if(!(redisCode.equalsIgnoreCase(user.getCode()))){
            return R.error("验证码错误, 请重新输入验证码!");
        }
        QueryWrapper<UserInfo2> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Account", user.getAccount());
        UserInfo2 userInfo = userInfo2Service.getOne(queryWrapper);
        if(userInfo==null){
            return R.error("用户名或密码错误");
        }
        String md5Password = getMd5(user.getPassword(), user.getAccount());
        System.out.println(md5Password+"======");
        if (!userInfo.getPassword().equals(md5Password)) {
            return R.error("用户名或密码错误");
        }
        UserInfo3Vo userInfo3 = userInfo2Service.getUserInfo(user.getAccount());

        QueryWrapper<Access> queryWrapperAccess = new QueryWrapper<>();
        queryWrapperAccess.eq("RoleId",userInfo.getFromRoleId());
        Access access = accessService.getOne(queryWrapperAccess);

        String token = TokenUtils.getToken(userInfo);
        map.put("UserInfo",userInfo3);
        map.put("Access",access);
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        response.addCookie(cookie);
        request.getSession(true).setAttribute(token,userInfo);
        redisTemplate.opsForValue().set("userInfo", userInfo3);
        return R.ok(map,token);
    }

    /**
     * 分页查询用户信息
     *
     * @return
     */
    @ApiOperation("查询用户信息")
    @GetMapping(value = "/pageQueryAllUserInfo")
    @UserinfoLoginToken
    public R pageQueryAllUserInfo(Integer pageNum, Integer pageSize, String query) {
//        query.replaceAll("\\s", "");
        Map<String, Object> map = new HashMap<>();
        if(pageNum==null||pageSize==null){
            pageNum=1;
            pageSize=10;
        }
        Page<UserInfo2Vo> page = new Page<>(pageNum, pageSize);
        UserInfo3Vo userInfo= (UserInfo3Vo) redisTemplate.opsForValue().get("userInfo");
        IPage<UserInfo2Vo> iPage = userInfo2Service.pageQueryAllUserInfo(page,query,userInfo.getCompId());
        map.put("UserInfo", iPage);
        return R.ok(map);
    }

    /**
     * 添加和修改用户信息
     *
     * @param userInfo
     * @return
     */
    @ApiOperation(value = "添加和修改用户信息",httpMethod = "POST")
    @PostMapping(value = "/addUpdateUserInfo")
    @UserinfoLoginToken
    public R addUpdateUserInfo(UserInfo2 userInfo, @RequestParam("signs") MultipartFile[] signs, HttpServletRequest request) {
        System.out.println(signs.length+"-------------"+userInfo);
        List list = new ArrayList();
        if (signs != null && signs.length > 0) {
            for (int i = 0; i < signs.length; i++) {
                MultipartFile file = signs[i];
                // 保存文件
                list = saveFile(request, file, list);
                if(list==null){
                    return R.error("上传类型错误");
                }
            }
            userInfo.setSign(StringUtils.join(Arrays.asList(list.toArray()),", "));
        }

        if(userInfo.getId()<1){
//            String account = userInfo.getAccount().replaceAll("\\s", "");
            String Account = userInfo.getAccount();
            String Password = "123456";
            String Phone = userInfo.getPhone();
            if (Account==null || Password==null || Phone == null){
                return R.error("输入的用户名或密码或身份证或手机号不能为空");
            }
            QueryWrapper<UserInfo2> queryAccount = new QueryWrapper<>();
            queryAccount.eq("Account",Account);
            UserInfo2 qa =  userInfo2Service.getOne(queryAccount);
            if (qa != null){
                return R.error("用户名已经被注册");
            }

            QueryWrapper<UserInfo2> queryPhone = new QueryWrapper<>();
            queryPhone.eq("Phone",Phone);
            UserInfo2 qp =  userInfo2Service.getOne(queryPhone);
            if (qp != null){
                return R.error("手机号已经被注册");
            }
            String md5Password = getMd5(Password, Account);
            userInfo.setPassword(md5Password);
            userInfo.setValid(0);
            userInfo.setCreateDate(new Date());
            UserInfo3Vo userInfoOne= (UserInfo3Vo) redisTemplate.opsForValue().get("userInfo");
            userInfo.setCompId(userInfoOne.getCompId());
            Boolean b = userInfo2Service.save(userInfo);
            if (!b) {
                return R.error("添加用户信息失败");
            }
            return R.ok("添加用户信息成功");
        }else {
            System.out.println(userInfo+"============");
            QueryWrapper<UserInfo2> query = new QueryWrapper<>();
            query.eq("id",userInfo.getId());
            Boolean p = userInfo2Service.update(userInfo,query);
            if (!p) {
                return R.error("修改用户信息失败");
            }
            return R.ok("修改用户信息成功");
        }
    }


    //md5加密规则
    private String getMd5(String password, String account) {
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((account + password + account).getBytes()).toUpperCase();
        }
        return password;
    }


    private List saveFile(HttpServletRequest request, MultipartFile file, List list) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                Set<String> typeSet = new HashSet<>();
                typeSet.add(".jpg");
                typeSet.add(".png");
                typeSet.add(".gif");
                String originalFileName = file.getOriginalFilename();
                String suffix = originalFileName.substring(originalFileName.lastIndexOf('.'));
                if(!typeSet.contains(suffix)){
                    return null;
                }
                //2、使用UUID生成新文件名
                String newFileName = UUID.randomUUID() + suffix;

                String filePath = "C:\\data\\payment-dome\\src\\main\\webapps\\upload\\" + newFileName;
                list.add("http://192.168.10.111:8081/upload/"+newFileName);
                File saveDir = new File(filePath);
                if (!saveDir.getParentFile().exists())
                    saveDir.getParentFile().mkdirs();
                // 转存文件
                file.transferTo(saveDir);
                return list;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            return null;
        }
        return list;
    }

    /**
     * 根据ID查看用户信息
     * @param id
     * @return
     */
    @ApiOperation("根据ID查看用户信息")
    @GetMapping(value = "/querUserInfo")
    @UserinfoLoginToken
    public R querUserInfo(Integer id) {
        Map<String,Object> map = new HashMap<>();
        UserInfo2Dto userInfo = userInfo2Service.getByUserInfoId(id);
        map.put("UserInfo",userInfo);
        return R.ok(map);
    }


    /**
     * 删除用户
     * @param id
     * @return
     */
    @ApiOperation("删除用户")
    @GetMapping(value = "/invalidUserInfo")
    @UserinfoLoginToken
    public R invalidUserInfo(Long id) {
        //将实体对象进行包装，包装为操作条件
        Boolean pi = userInfo2Service.removeById(id);
        if (!pi) {
            return R.error("删除用户信息失败");
        }
        return R.ok("删除用户信息成功");
    }


    /**
     * 生成验证码
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @ApiOperation("生成验证码")
    @GetMapping("/getCodeImg")
    public ModelAndView createCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        // 自定义宽、高、字数和干扰线的条数
        IdentifyCode code = new IdentifyCode(100, 30, 4, 10);
        redisTemplate.opsForValue().set("code",code.getCode());
        // 响应图片
        ServletOutputStream out = response.getOutputStream();
        code.write(out);
        try {
            out.flush();
        } finally {
            out.close();
        }
        return null;
    }

    /**
     * 生成验证码
     */
    public static final String VERIFY_ID = "verid";


    /**
     * 生成验证码
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @ApiOperation("生成验证码")
    @GetMapping("/getCodeImg1")
    public String createCode1(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        // 自定义宽、高、字数和干扰线的条数
        IdentifyCode code = new IdentifyCode(100, 30, 4, 10);

        String verid = UUID.randomUUID().toString().replaceAll("-", "");

        Cookie cookie=new Cookie(VERIFY_ID, verid);        //将uuid串存入cookie
        response.addCookie(cookie);
        StringBuilder verifyKey = new StringBuilder();
        verifyKey.append(verid);
        System.out.println(verifyKey.toString()+"========"+code.getCode());
        redisTemplate.opsForValue().set(verifyKey.toString(), code.getCode(), 3600, TimeUnit.SECONDS);     //将验证码存入redis

        // 响应图片
        ServletOutputStream out = response.getOutputStream();
        code.write(out);
        try {
            out.flush();
        } finally {
            out.close();
        }
        return null;
    }

    /**
     * 获取验证码
     * @param request
     * @return
     * @throws Exception
     */
    @ApiOperation("生成验证码1")
    @GetMapping("/getCodeImg2")
    public void createCode1(HttpServletRequest request) {
        String verid1= CookieUtil.getCookieValue(request,"verid");      //从cookie获取uuid串
        System.out.println(verid1+"=====");
        String redisVerCode= (String) redisTemplate.opsForValue().get(verid1);       //从redis获取验证码
        System.out.println(redisVerCode+"=============");
//        String redisVerCode=null;
//        if(org.springframework.util.StringUtils.hasText(verid1)){
//            redisVerCode= (String) redisTemplate.opsForValue().get(verid1);       //从redis获取验证码
//            System.out.println(redisVerCode+"=============");
//        }
    }


    /**
     * 验证验证码是否一致
     * @param request
     * @return
     */
    @GetMapping(value = "check")
    @ResponseBody
    public boolean checkCode(HttpServletRequest request) {
        String oldStr = (String) request.getSession().getAttribute(
                "myCode");
        String newStr = request.getParameter("code");
        if (oldStr.equalsIgnoreCase(newStr)) {
            return true;
        }
        return false;
    }

    /**
     * 查询所有用户名称
     */
    @ApiOperation("查询所有用户名称")
    @GetMapping(value = "/queryAllName")
    @UserinfoLoginToken
    public R queryAllName() {
        Map<String,Object> map = new HashMap<>();
        List<UserInfoVo> userInfoVos =userInfo2Service.queryAllName();
        map.put("UserInfo",userInfoVos);
        return R.ok(map);
    }


    /**
     *
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @ApiOperation("修改用户密码")
    @GetMapping (value = "/updatePwd")
    @UserinfoLoginToken
    public R updatePwd(Integer Id,String oldPassword,String newPassword ){
        QueryWrapper<UserInfo2> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Id",Id);
        UserInfo2 userInfo=userInfo2Service.getOne(queryWrapper);
        String md5Password = getMd5(oldPassword, userInfo.getAccount());
        if (!userInfo.getPassword().equals(md5Password)) {
            return R.error("密码不正确");
        }
        String md5PassWord = getMd5(newPassword, userInfo.getAccount());
        QueryWrapper<UserInfo2> query= new QueryWrapper<>();
        query.eq("Id",userInfo.getId());
        UserInfo2 user = new UserInfo2();
        user.setPassword(md5PassWord);
        Boolean ui = userInfo2Service.update(user,queryWrapper);
        if (!ui) {
            return R.error("修改密码失败");
        }
        return R.ok();
    }


    /**
     * 修改管理员密码
     *
     * @param Id
     * @return
     */
    @ApiOperation("修改管理员密码")
    @GetMapping(value = "/ChangePassword")
    @UserinfoLoginToken
    public R ChangePassword(Integer Id,String adminPassword,String newPassword) {
        UserInfo2 userInfo = userInfo2Service.getById(Id);
        System.out.println(userInfo);
        String md5Password2 = getMd5(adminPassword, userInfo.getAccount());
        System.out.println(md5Password2+"===========");
        if(md5Password2.equals(getMd5("123456",userInfo.getAccount()))){
            String md5Password = getMd5(newPassword, userInfo.getAccount());
            userInfo.setPassword(md5Password);
            QueryWrapper<UserInfo2> queryWrapper= new QueryWrapper<>();
            queryWrapper.eq("Id",userInfo.getId());
            Boolean ui = userInfo2Service.update(userInfo,queryWrapper);
            if (!ui) {
                return R.error("修改密码失败");
            }
            return R.ok("密码修改成功");
        }else {
            return R.error("管理员密码错误");
        }
    }



    @ApiOperation("退出登录")
    @GetMapping(value="/logout")
    @UserinfoLoginToken
    public R logout(String token,HttpServletRequest request, HttpServletResponse response){
//        request.getSession().invalidate();
        String sessionIdStr = "";
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    sessionIdStr = cookie.getValue();
                    sessionUsermap.remove(sessionIdStr);//移除静态变量中的值
                }
            }
        }
        request.getSession().removeAttribute(token);
//        Cookie deleteNewCookie = new Cookie("token", null);
//        deleteNewCookie.setMaxAge(0); // 删除该Cookie
//        deleteNewCookie.setPath("/");
//        response.addCookie(deleteNewCookie);
        return R.ok("退出登录成功");
    }


    /**
     * app登录接口
     *
     * @param user
     * @return
     */
    @ApiOperation("app登录接口")
    @PostMapping(value = "/appLogin")
    public R appLogin(UserInfo2 user, HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<>();
        if (StringUtils.isEmpty(user.getAccount()) || StringUtils.isEmpty(user.getPassword())) {
            return R.error("输入的用户名或密码或验证码不能为空");
        }
        QueryWrapper<UserInfo2> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Account", user.getAccount());
        UserInfo2 userInfo = userInfo2Service.getOne(queryWrapper);
        if(userInfo==null){
            return R.error("用户名或密码错误");
        }
        String md5Password = getMd5(user.getPassword(), user.getAccount());
        if (!userInfo.getPassword().equals(md5Password)) {
            return R.error("用户名或密码错误");
        }
        String token = TokenUtils.getToken(userInfo);
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        response.addCookie(cookie);
        request.getSession(true).setAttribute(token,userInfo);
        return R.ok(map,token);
    }
}

