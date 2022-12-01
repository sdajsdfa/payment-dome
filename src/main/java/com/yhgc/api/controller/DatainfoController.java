package com.yhgc.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yhgc.api.entity.Datainfo;
import com.yhgc.api.service.DatainfoService;
import com.yhgc.api.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * <p>
 * 数据 前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-27
 */
@Api(tags = "数据")
@RestController
@RequestMapping("/datainfo")
public class DatainfoController {

    @Resource
    private DatainfoService datainfoService;

    /**
     *  上传检测数据
     * @param datainfo
     * @return
     */
    @ApiOperation("上传检测数据")
    @PostMapping(value = "/uploadTestInfo")
    public R uploadTestInfo(@RequestBody Datainfo datainfo) {
        Map<String,Object> map = new HashMap<>();
        datainfo.setAnalyFileTime(new Date());
        datainfo.setOrginFileTime(new Date());
        datainfo.setUploadTime(new Date());
        datainfo.setTestTime(new Date());
        datainfo.setDataStatus(0);
        Boolean ui = datainfoService.save(datainfo);
        if (!ui) {
            return R.error("添加失败");
        }
        map.put("dataId",datainfo.getDataId());
        map.put("uploadTime",datainfo.getUploadTime());
        return R.ok(map);
    }

    /**
     * 删除检验信息
     * @param id
     * @return
     */
    @ApiOperation("删除检验信息")
    @GetMapping (value = "/deleteTestInfo")
    public R deleteTestInfo(Long id) {
        QueryWrapper<Datainfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        Datainfo datainfo = new Datainfo();
        datainfo.setDataStatus(1);
        //将实体对象进行包装，包装为操作条件
        Boolean ui =  datainfoService.update(datainfo,queryWrapper);
        if (!ui) {
            return R.error("删除检验信息失败");
        }
        return R.ok();
    }

    /**
     *查询文件详细信息
     * @param dataId
     * @return
     */
    @ApiOperation("查询文件详细信息")
    @GetMapping(value = "/queryFileInfo")
    public R queryFileInfo(Integer dataId) {
        Datainfo datainfo = datainfoService.getById(dataId);
        return R.ok(datainfo);
    }

    /**
     * 上传原始文件
     * @param multipartFile
     * @param unitId
     * @param serialNo
     * @param fileType
     * @return
     */
    @ApiOperation("上传原始文件")
    @PostMapping("/uploadOriginFile")
    public R uploadOriginFile(MultipartFile multipartFile, Integer dataId, Integer unitId, String serialNo , Integer fileType)
    {
        //指定存放上传文件的目录
        String fileDir = "C:\\ftp\\home\\"+unitId+"\\"+serialNo;
        File dir = new File(fileDir);

        //判断目录是否存在，不存在则创建目录
        if (!dir.exists()){
            dir.mkdirs();
        }

        //生成新文件名，防止文件名重复而导致文件覆盖
        //1、获取原文件后缀名 .img .jpg ....
        String originalFileName = multipartFile.getOriginalFilename();
        String suffix = originalFileName.substring(originalFileName.lastIndexOf('.'));
        //2、使用UUID生成新文件名
        String newFileName = UUID.randomUUID() + suffix;

        //生成文件
        File file = new File(dir, newFileName);
        //传输内容
        try {
            multipartFile.transferTo(file);
            Datainfo datainfo = new Datainfo();
            datainfo.setAnalysisStatus(0);
            datainfo.setAnalyFileTime(new Date());
            QueryWrapper<Datainfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("dataId", dataId);
            Boolean da = datainfoService.update(datainfo,queryWrapper);
            if(!da){
                return R.error("文件修改失败");
            }
        } catch (IOException e) {
            System.out.println("上传文件失败！");
            e.printStackTrace();
        }
        HashMap<String,Object> hashMap = new HashMap<>();
        //上传至ftp服务器
        //1、上传文件
        if (uploadToFtp(file,unitId,serialNo,fileType)){
            if(fileType==1){
                hashMap.put("orginFilePath",file);
                return R.ok(hashMap);
            }else {
                hashMap.put("analysisFilePath",file);
                return R.ok(hashMap);
            }
        }else {
            //2、删除本地文件
            file.delete();
            return R.error("上传至ftp服务器失败!");
        }

    }

    private boolean uploadToFtp(File file,Integer unitId, String serialNo,Integer fileType){
        FTPClient ftpClient = new FTPClient();
        try {
            //连接ftp服务器 参数填服务器的ip
            ftpClient.connect("120.79.64.245");

            //进行登录 参数分别为账号 密码
            ftpClient.login("ftptest","Yi123456");

            if(fileType==1) {
                //改变工作目录（按自己需要是否改变）
                String fileDir = "D:\\ftp\\"+unitId+"\\"+serialNo+"\\origin";
                File dir = new File(fileDir);

                //判断目录是否存在，不存在则创建目录
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                //只能选择local_root下已存在的目录
                ftpClient.changeWorkingDirectory(unitId+"/"+serialNo+"/origin/");
            }else {
                //改变工作目录（按自己需要是否改变）
                String fileDir = "D:\\ftp\\"+unitId+"\\"+serialNo+"\\analysis";
                File dir = new File(fileDir);

                //判断目录是否存在，不存在则创建目录
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                //只能选择local_root下已存在的目录
                ftpClient.changeWorkingDirectory(unitId+"/"+serialNo+"/analysis/");
            }
            //设置文件类型为二进制文件
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            //开启被动模式（按自己如何配置的ftp服务器来决定是否开启）
            ftpClient.enterLocalPassiveMode();

            //上传文件 参数：上传后的文件名，输入流
            ftpClient.storeFile(file.getName(), new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 下载原始文件
     * @param path
     * @param response
     * @return
     */
    @ApiOperation("下载原始文件")
    @PostMapping("/downloadOriginFile")
    public void downloadOriginFile(String path, HttpServletResponse response){
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     *个人数据统计
     * @param userId
     */
    @ApiOperation("个人数据统计")
    @PostMapping("/countPersonalData")
    public R countPersonalData(Integer userId){
       Map<String,Object> map = new HashMap();
       Integer projectNum = datainfoService.countProjectinfo(userId);
       Integer fileNum =  datainfoService.countFile(userId);
       Integer analysisNum =  datainfoService.countAnalysis(userId);
       Integer machineNum = datainfoService.countMachine(userId);
       List<Map<Date,Integer>> sevenDaysUploadNum =  datainfoService.countSevenDaysUpload(userId);
       List<Map<Date,Integer>> sevenDaysAnalysisNum = datainfoService.countSevenDaysAnalysis(userId);
       map.put("projectNum",projectNum);
       map.put("fileNum",fileNum);
       map.put("analysisNum",analysisNum);
       map.put("machineNum",machineNum);
       map.put("sevenDaysUploadNum",sevenDaysUploadNum);
       map.put("sevenDaysAnalysisNum",sevenDaysAnalysisNum);
       return R.ok(map);
    }
}

