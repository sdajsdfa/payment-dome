package com.yhgc.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import com.yhgc.api.entity.Appname;
import com.yhgc.api.entity.Appversionlist;
import com.yhgc.api.enums.AppStatusEnum;
import com.yhgc.api.service.AppnameService;
import com.yhgc.api.service.AppversionlistService;
import com.yhgc.api.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * <p>
 * APP版本列表 前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2022-12-06
 */
@Api(tags = "APP版本列表")
@RestController
@RequestMapping("/appversionlist")
public class AppversionlistController {

    @Resource
    private AppversionlistService appversionlistService;


    @Resource
    private AppnameService appnameService;

    /**
     *查询所有APP版本列表
     * @return
     */
    @ApiOperation("查询APP版本列表")
    @GetMapping(value = "/queryAllServerlist")
    public R queryAllAppversionlist() {
        Map<String, Object> map = new HashMap<String, Object>();
        QueryWrapper<Appname> query = new QueryWrapper<>();
        query.eq("AppStatus", AppStatusEnum.NORMAL.getCode());
        query.last("limit 1");
        Appname  appname = appnameService.getOne(query);

        QueryWrapper<Appversionlist> queryWrapper = new QueryWrapper<>();
        if(appname==null){
            queryWrapper.eq("AppStatus",AppStatusEnum.NORMAL.getCode());
            List<Appversionlist>  list = appversionlistService.list(queryWrapper.orderByDesc("id"));
            map.put("list",list);
            return R.ok(map);
        }
        queryWrapper.eq("AppId",appname.getAppId());
        queryWrapper.eq("AppStatus",AppStatusEnum.NORMAL.getCode());
        List<Appversionlist>  list = appversionlistService.list(queryWrapper.orderByDesc("id"));
        map.put("appname",appname);
        map.put("list",list);
        return R.ok(map);
    }

    /**
     *查询APP名称版本列表
     * @return
     */
    @ApiOperation("查询APP版本列表信息")
    @PostMapping(value = "/queryAppName")
    public R queryAppName(Appversionlist appversionlist) {
        QueryWrapper<Appversionlist> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("AppId", appversionlist.getAppId());
        queryWrapper.eq("AppStatus",AppStatusEnum.NORMAL.getCode());
        List<Appversionlist>  list = appversionlistService.list(queryWrapper.orderByDesc("id"));
        return R.ok(list);
    }

    @ResponseBody
    @RequestMapping(value = "/apk_upload" ,method = RequestMethod.POST)
    public Map<String, Object> uploadApkFile(Long id,HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.setCharacterEncoding("UTF-8");
        Map<String, Object> json = new HashMap<String, Object>();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        /** 页面控件的文件流* */
        MultipartFile multipartFile = null;
        Map map =multipartRequest.getFileMap();
        for (Iterator i = map.keySet().iterator(); i.hasNext();) {
            Object obj = i.next();
            multipartFile=(MultipartFile) map.get(obj);

        }
        /** 获取文件的后缀* */
        String filename = multipartFile.getOriginalFilename();

        InputStream inputStream;
        String path = "";
        String newVersionName = "";
        String fileMd5 = "";
        try {
            String[] str =filename.split(".apk");
            String name = null;
            for (int i=0;i <str.length;i++){
                name = str[i];
            }
            inputStream = multipartFile.getInputStream();

            File tmpFile = new File("/root/app/"+filename);

            boolean res = tmpFile.createNewFile();
            if(!res)System.out.println("创建失败！");

            fileMd5 = Files.hash(tmpFile, Hashing.md5()).toString();
            FileUtils.copyInputStreamToFile(inputStream, tmpFile);
            FTPClient ftpClient = new FTPClient();
            try {
                //连接ftp服务器 参数填服务器的ip
                ftpClient.connect("120.79.64.245");

                //进行登录 参数分别为账号 密码
                ftpClient.login("ftptest","Yi123456");

                ftpClient.makeDirectory(String.valueOf(id));
                //只能选择local_root下已存在的目录
                ftpClient.changeWorkingDirectory(String.valueOf(id));

                //设置文件类型为二进制文件
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

                //开启被动模式（按自己如何配置的ftp服务器来决定是否开启）
                ftpClient.enterLocalPassiveMode();
                //上传文件 参数：上传后的文件名，输入流
                ftpClient.storeFile(tmpFile.getName(), new FileInputStream(tmpFile));
//                tmpFile.delete();
                String RegularFile="http://120.79.64.245/"+id+"/"+tmpFile.getName();
               if(filename.substring(filename.lastIndexOf(".")).equals(".apk")){
                   json.put("RegularFile", RegularFile);
                   json.put("oldFileName", filename);
                   json.put("picName", tmpFile.getName());
               }else if(filename.substring(filename.lastIndexOf(".")).equals(".json")) {
                   json.put("JsonFile", RegularFile);
                   json.put("oldFileName", filename);
                   json.put("picName", tmpFile.getName());
               }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        json.put("newVersionName", newVersionName);
//        json.put("fileMd5", fileMd5);
//        json.put("message", "应用上传成功");
//        json.put("status", true);
//        json.put("filePath", path);
        return json;
    }

    @RequestMapping("/download")
    public void downloadFtpFile(String downLoadName, HttpServletResponse response) throws IOException {
        // 1.创建FTPClient的用户对象
        FTPClient ftpClient = new FTPClient();
        // 连接服务器
        ftpClient.connect("120.79.64.245", 21);

        // 2.登录【用户名，密码】
        ftpClient.login("ftptest", "Yi123456");

        File file = new File(downLoadName);
        // 取得文件名。
        String fileName = file.getName();

        try (FileOutputStream out = new FileOutputStream("C:\\data\\payment-dome\\src\\main\\webapps\\upload\\"+fileName)) {
            // 切换至文件的位置
            ftpClient.changeWorkingDirectory("aaa");
            // 设置文件的类型
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            // 参数1：文件保存的名称
            // 参数2：下载至本地的位置
            ftpClient.retrieveFile(fileName, out);

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream("C:\\data\\payment-dome\\src\\main\\webapps\\upload\\"+fileName));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                // 断开与服务器的连接
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



//    /**
//     *查询APP名称版本列表
//     * @return
//     */
//    @ApiOperation("查询APP版本列表信息")
//    @GetMapping(value = "/queryServerlist")
//    public R queryServerlist(Long AppId) {
//        QueryWrapper<Appversionlist> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("AppId", AppId);
//        List<Appversionlist>  list = appversionlistService.list(queryWrapper);
//        return R.ok(list);
//    }

    /**
     *添加APP版本列表
     * @return
     */
    @ApiOperation("添加APP版本列表")
    @PostMapping (value = "/addAppversionlist")
    public R addAppversionlist(Appversionlist appversionlist, BindingResult bindingResult) {
        Boolean app = appversionlistService.save(appversionlist);
        if(!app){
            R.error("添加APP版本列表失败");
        }
        QueryWrapper<Appversionlist> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("AppStatus", AppStatusEnum.NORMAL.getCode());
        queryWrapper.eq("AppId", appversionlist.getAppId());
        List<Appversionlist> list= appversionlistService.list(queryWrapper.orderByDesc("id"));
        return R.ok(list);

    }

    /**
     *修改APP版本列表
     * @return
     */
    @ApiOperation("修改APP版本列表")
    @PostMapping (value = "/updateAppversionlist")
    public R updateAppversionlist(Appversionlist appversionlist) {
        Map<String, Object> map = new HashMap<String, Object>();
        Boolean app = appversionlistService.updateById(appversionlist);
        if(!app){
            R.error("修改APP版本列表失败");
        }
        Appversionlist aversion = appversionlistService.getById(appversionlist.getId());
        QueryWrapper<Appversionlist> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("AppId", appversionlist.getAppId());
        queryWrapper.eq("AppStatus",AppStatusEnum.NORMAL.getCode());
        List<Appversionlist> list= appversionlistService.list(queryWrapper.orderByDesc("id"));
        map.put("aversion",aversion);
        map.put("listapp",list);
        return R.ok(map);
    }

    /**
     *删除APP版本列表
     * @return
     */
    @ApiOperation("删除APP版本列表")
    @PostMapping (value = "/deleteAppversionlist")
    public R deleteAppversionlist(Appversionlist appversionlist) {
        appversionlist.setAppStatus(AppStatusEnum.DELETE.getCode());
        Boolean app = appversionlistService.updateById(appversionlist);
        if(!app){
            R.error("删除APP版本列表失败");
        }
        QueryWrapper<Appversionlist> Wrapper = new QueryWrapper<>();
        Wrapper.eq("AppId",appversionlist.getAppId());
        Wrapper.eq("AppStatus",AppStatusEnum.NORMAL.getCode());
        List<Appversionlist> list2 = appversionlistService.list(Wrapper.orderByDesc("id"));
        return R.ok(list2);
    }

}

