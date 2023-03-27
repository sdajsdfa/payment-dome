package com.yhgc.api.controller;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.deploy.util.StringUtils;
import com.yhgc.api.entity.MethodInfo;
import com.yhgc.api.entity.ProjectInfo;
import com.yhgc.api.entity.UserInfo;
import com.yhgc.api.enums.StatusEnum;
import com.yhgc.api.service.UserInfoService;
import com.yhgc.api.service.UserinfoLoginToken;
import com.yhgc.api.util.R;
import com.yhgc.api.util.TokenUtils;
import com.yhgc.api.vo.MethodInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
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
@RequestMapping("/userInfo")
@Transactional
public class UserInfoController {

    @Resource
    private UserInfoService userinfoService;

    @Resource
    private RedisTemplate redisTemplate;

    public static Map<String, UserInfo> sessionUsermap = new HashMap<>();



    /**
     * 查询用户信息
     *
     * @return
     */
    @ApiOperation("查询用户信息")
    @GetMapping(value = "/queryAllUserInfo")
    public R queryAllUserInfo(Integer pageNum, Integer pageSize, String query) {
        query.replaceAll("\\s", "");
        Map<String, Object> map = new HashMap<>();
        if(pageNum==null||pageSize==null){
            pageNum=1;
            pageSize=10;
        }
        Page<UserInfo> page = new Page<>(pageNum, pageSize);
        IPage<UserInfo> iPage = userinfoService.searchPage(page,query);
        map.put("userInfo", iPage);
        return R.ok(map);
    }

    /**
     * 登录接口
     *
     * @param user
     * @return
     */
    @ApiOperation("登录接口")
    @PostMapping(value = "/login")
    public R login(UserInfo user, HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<>();
        if (user.getAccount() == null || user.getPassWord() == null ) {
            return R.error("输入的用户名或密码不能为空");
        }
//        if(request.getSession(true).getAttribute("code")==null){
//            return R.error("验证码不能为空, 请输入验证码!");
//        }
//        if(!(request.getSession(true).getAttribute("code").equals(code))){
//            return R.error("验证码错误, 请重新输入验证码!");
//        }
        System.out.println(user.getAccount());
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", user.getAccount());
//        queryWrapper.eq("status", 0);
        UserInfo userInfo = userinfoService.getOne(queryWrapper);
        if(userInfo==null){
            return R.error("用户名或密码错误");
        }
        String md5Password = getMd5(user.getPassWord(), user.getAccount());
        if (!userInfo.getPassWord().equals(md5Password)) {
            return R.error("用户名或密码错误");
        }
        String token = TokenUtils.getToken(userInfo);
        map.put("userInfo",userInfo);
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        response.addCookie(cookie);
        request.getSession(true).setAttribute(token,userInfo);
//      redisTemplate.opsForValue().set(token, userinfo);
        return R.ok(map,token);
    }

    @ApiOperation("退出登录")
    @GetMapping(value="/logout")
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
//        String account = userinfo.getAccount().replaceAll("\\s", "");
        String account = userinfo.getAccount();
        String password = userinfo.getPassWord();
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
        userinfo.setPassWord(md5Password);
        userinfo.setStatus(0);
        userinfo.setCreateTime(new Date());
        Boolean b = userinfoService.save(userinfo);
        if (!b) {
            return R.error("添加失败");
        }
        return R.ok(userinfo);
    }

    /**
     * 添加和修改用户信息
     *
     * @param userInfo
     * @return
     */
    @ApiOperation(value = "添加和修改用户信息",httpMethod = "POST")
    @PostMapping (value = "/addUpdateUserInfo")
    @UserinfoLoginToken
    public R addUpdateUserInfo(UserInfo userInfo, @RequestParam("files") MultipartFile[] files, HttpServletRequest request) {
        System.out.println(files.length+"-------------");
        List list = new ArrayList();
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                // 保存文件
                list = saveFile(request, file, list);
                if(list==null){
                    return R.error("上传类型错误");
                }
            }
        }
        userInfo.setPicture(StringUtils.join(Arrays.asList(list.toArray()),", "));
        if(userInfo.getId()<0){
//            String account = userInfo.getAccount().replaceAll("\\s", "");
            String account = userInfo.getAccount();
            String password = "123456";
            String idCard = userInfo.getIdCard();
            String phoneNum = userInfo.getPhoneNum();
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
            String md5Password = getMd5(password, account);
            userInfo.setPassWord(md5Password);
            userInfo.setStatus(0);
            userInfo.setCreateTime(new Date());
            Boolean b = userinfoService.save(userInfo);
            if (!b) {
                return R.error("添加用户信息失败");
            }
            return R.ok("添加用户信息成功");
        }else {
            QueryWrapper<UserInfo> query = new QueryWrapper<>();
            query.eq("id",userInfo.getId());
            Boolean p = userinfoService.update(userInfo,query);
            if (!p) {
                return R.error("修改用户信息失败");
            }
            return R.ok("修改用户信息成功");
        }
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
    @GetMapping(value = "/getByIdUser")
    public R getByIdUser(Long id) {
        Map<String,Object> map = new HashMap<>();
        UserInfo userInfo = userinfoService.getById(id);
        map.put("userInfo",userInfo);
        return R.ok(map);
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
     * @param id
     * @return
     */
    @ApiOperation("删除人员")
    @GetMapping(value = "/invalidUser")
    public R invalidUser(Long id) {
        //将实体对象进行包装，包装为操作条件
        Boolean pi = userinfoService.removeById(id);
        if (!pi) {
            return R.error("删除用户信息失败");
        }
        return R.ok("删除用户信息成功");
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
    @GetMapping(value = "/getCodeImg")
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
//        for(int i=0;i<4;i++) {
//            text +=(int) (Math.random()*10);
//        }
        Random random = new Random();
        for(int i = 0; i < 4; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                text += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                text += String.valueOf(random.nextInt(10));
            }
        }
        //字符串输出内容，水平起始坐标，垂直起始坐标。
        g.drawString(text, 17, 24);
        //画线条
        for (int i = 0; i < 10; i++) {
            g.setColor(new Color((int) (Math.random()*255), (int) (Math.random()*255), (int) (Math.random()*255)));
            g.drawLine((int) (Math.random()*50),(int) (Math.random()*30),(int) (Math.random()*80),(int) (Math.random()*80));
        }
        //设置session
        request.getSession(true).setAttribute("code",text);
        request.getSession(true).setMaxInactiveInterval(60);
        //response.setContentType("image/png");
        //输出图像
        //ImageIO.write(image, "png", new FileOutputStream("C:/Users/H/Desktop/"+tet+".png"));
        ImageIO.write(image, "png",response.getOutputStream());
        g.dispose();
    }

    //获取保存在session中的验证码
    @ApiOperation("获取图片验证码")
    @GetMapping(value = "/getCode")
    public String getCode(HttpServletRequest request){
        return (String) request.getSession(true).getAttribute("code");
    }

    /**
     *
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @ApiOperation("修改用户密码")
    @GetMapping (value = "/updatePwd")
    public R updatePwd(Long id,String oldPassword,String newPassword ){
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        queryWrapper.eq("status",0);
        UserInfo userinfo=userinfoService.getOne(queryWrapper);
        String md5Password = getMd5(oldPassword, userinfo.getAccount());
        System.out.println(md5Password);
        if (!userinfo.getPassWord().equals(md5Password)) {
            return R.error("密码不正确");
        }
        String md5PassWord = getMd5(newPassword, userinfo.getAccount());
        QueryWrapper<UserInfo> query= new QueryWrapper<>();
        query.eq("id",userinfo.getId());
        UserInfo user = new UserInfo();
        user.setPassWord(md5PassWord);
        Boolean ui = userinfoService.update(user,queryWrapper);
        if (!ui) {
            return R.error("修改密码失败");
        }
        return R.ok();
    }


    /**
     * 修改管理员密码
     *
     * @param id
     * @return
     */
    @ApiOperation("修改管理员密码")
    @GetMapping(value = "/ChangePassword")
//    @UserinfoLoginToken
    public R ChangePassword(Long id,String adminPassword,String newPassword) {
        UserInfo userInfo = userinfoService.getById(id);
        System.out.println(userInfo);
        String md5Password2 = getMd5(adminPassword, userInfo.getAccount());
        System.out.println(md5Password2+"====="+userInfo.getPassWord());
        if(md5Password2.equals(userInfo.getPassWord())){
            String md5Password = getMd5(newPassword, userInfo.getAccount());
            userInfo.setPassWord(md5Password);
            QueryWrapper<UserInfo> queryWrapper= new QueryWrapper<>();
            queryWrapper.eq("id",userInfo.getId());
            Boolean ui = userinfoService.update(userInfo,queryWrapper);
            if (!ui) {
                return R.error("修改密码失败");
            }
            return R.ok("密码修改成功");
        }else {
            return R.error("管理员密码错误");
        }
    }
}

