package com.yhgc.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.deploy.util.StringUtils;
import com.yhgc.api.entity.MethodInfo;
import com.yhgc.api.entity.ProjectInfo;
import com.yhgc.api.enums.StatusEnum;
import com.yhgc.api.service.MethodInfoService;
import com.yhgc.api.service.ProjectFilesService;
import com.yhgc.api.service.ProjectInfoService;
import com.yhgc.api.service.UserinfoLoginToken;
import com.yhgc.api.util.R;
import com.yhgc.api.vo.MethodInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * <p>
 * 检测方法分类 前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-27
 */
@Api(tags = "检测方法分类")
@RestController
@RequestMapping("/methodInfo")
@Transactional
public class MethodInfoController {

    @Resource
    private MethodInfoService methodInfoService;

    @Resource
    private ProjectFilesService projectfilesService;

    @Resource
    private ProjectInfoService projectinfoService;



    @ApiOperation("测试")
    @GetMapping(value = "/queryByMethodInfo1212")
    public void queryByMethodInfo1212() {
        String Number ="";
        SimpleDateFormat f = new SimpleDateFormat("yyyy");
        String date = f.format(new Date(System.currentTimeMillis()));
        List<MethodInfo> list = methodInfoService.selectMenuInfo();
        if(list.size() > 0){
            int count = list.size();
            String d =list.get(count-1).getSerialNo();
            int intNumber = Integer.parseInt(d.substring(6,10));
            intNumber++;
           Number = String.valueOf(intNumber);
            for (int i = 0; i < 4; i++){
                Number = Number.length() < 4 ? "0" + Number : Number;
            }
            Number = "W" + date+ "-" + Number + "DY";
        }else{
           Number = "W" + date + "-"+ "0001DY";
        }
    }

    /**
     * 查询检测方法分类
     *
     * @param id
     * @return
     */
    @ApiOperation("查询检测方法分类")
    @GetMapping(value = "/queryByMethodInfo")
    @UserinfoLoginToken
    public R queryByMethodInfo(Long id) {
        Map<String, Object> map = new HashMap<>();
        MethodInfo methodInfo = methodInfoService.getByIdMethodInfo(id);
        map.put("methodInfoDetailed",methodInfo);
        return R.ok(map);
    }


    /**
     * 查询检测方法分类
     *
     * @return
     */
    @ApiOperation("查询检测方法分类")
    @GetMapping(value = "/queryAllMethodInfo")
//    @UserinfoLoginToken
    public R queryAllMethodInfo(Integer pageNum, Integer pageSize, String query) {
        Map<String, Object> map = new HashMap<>();
        MethodInfo methodInfo = new MethodInfo();
        if(pageNum==null||pageSize==null){
            pageNum=1;
            pageSize=10;
        }
        Page<MethodInfo> page = new Page<>(pageNum, pageSize);
        if (!(query.equals(""))) {
            if (ProjectInfoController.isNumeric(query)) {
                methodInfo.setId(Long.valueOf(query));
            } else {
                methodInfo.setTestMthod(query);
            }
        } else {
            methodInfo.setTestMthod("");
        }
        System.out.println(methodInfo.getId());
        QueryWrapper<MethodInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 2);
        queryWrapper.like("testMthod", methodInfo.getTestMthod()).or().eq("id", methodInfo.getId());
        IPage<MethodInfo> methodInfoIPage = methodInfoService.page(page, queryWrapper);
        map.put("methodInfo", methodInfoIPage);
        return R.ok(map);
    }

    /**
     * 查询检测方法分类
     *
     * @return
     */
    @ApiOperation("查询检测方法分类")
    @GetMapping(value = "/queryAllByMethodInfo")
//    @UserinfoLoginToken
    public R queryAllByMethodInfo(Integer pageNum, Integer pageSize, String query) {
        Map<String, Object> map = new HashMap<>();
        if(pageNum==null||pageSize==null){
            pageNum=1;
            pageSize=10;
        }
        Page<MethodInfoVo> page = new Page<>(pageNum, pageSize);
        IPage<MethodInfoVo> iPage = methodInfoService.searchPage(page,query);
        map.put("methodInfo", iPage);
        return R.ok(map);
    }



    /**
     * 添加和修改检测方法分类
     *
     * @param methodInfo
     * @return
     */
    @ApiOperation(value = "添加和修改检测方法分类",httpMethod = "POST")
    @PostMapping (value = "/addUpdateMethodInfo")
    @UserinfoLoginToken
    public R addUpdateMethodInfo(@RequestBody MethodInfo methodInfo) {
        Map<String,Object> map = new HashMap<>();
        if(methodInfo.getId()<0){
            if(methodInfo.getMethodId()==null){
                methodInfo.setMethodId(-1);
            }
            if(methodInfo.getMethodId()!=-1){
                if(projectinfoService.getById(methodInfo.getMethodId())==null){
                    return R.error("必须要有工程名称");
                };
            }
            methodInfo.setCreateTime(new Date());
            methodInfo.setStatus(0);
            int ui = methodInfoService.saveMethodInfo(methodInfo);
            if (ui==1) {
                map.put("methodInfo",methodInfoService.getById(methodInfo.getId()));
                return R.ok(map);
            }
            return R.error("添加检测项目失败");
        }else {
            String Number ="";
            SimpleDateFormat f = new SimpleDateFormat("yyyy");
            String date = f.format(new Date(System.currentTimeMillis()));
            List<MethodInfo> list = methodInfoService.selectMenuInfo();
            if(list.size() > 0){
                int count = list.size();
                String d =list.get(count-1).getSerialNo();
                int intNumber = Integer.parseInt(d.substring(6,10));
                intNumber++;
                Number = String.valueOf(intNumber);
                for (int i = 0; i < 4; i++){
                    Number = Number.length() < 4 ? "0" + Number : Number;
                }
                Number = "W" + date+ "-" + Number + "DY";
            }else{
                Number = "W" + date + "-"+ "0001DY";
            }
            QueryWrapper<MethodInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", methodInfo.getId());
            methodInfo.setCreateTime(new Date());
            methodInfo.setSerialNo(Number);
            Boolean p = methodInfoService.update(methodInfo,queryWrapper);
            if (!p) {
                return R.error("修改检测方法分类失败");
            }
            return R.ok("修改检测方法分类成功");
        }
    }




    /**
     *  添加检测项目
     * @param methodInfo
     * @return
     */
    @ApiOperation("添加检测项目")
    @PostMapping("/saveMethodInfo")
    @UserinfoLoginToken
    public R saveMethodInfo(@RequestBody MethodInfo methodInfo) {
        Map<String,Object> map = new HashMap<>();
        methodInfo.setCreateTime(new Date());
        methodInfo.setStatus(0);
        methodInfo.setMethodId(-1);
        int ui = methodInfoService.saveMethodInfo(methodInfo);
        if (ui==1) {
            QueryWrapper<MethodInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", methodInfo.getId());
            map.put("methodInfo",methodInfoService.list(queryWrapper));
            return R.ok(map);
        }
        return R.error("添加检测项目失败");
    }

    /**
     *  修改检测项目
     * @param methodInfo
     * @return
     */
    @ApiOperation("修改检测项目")
    @PostMapping("/updateMethodInfo")
    @UserinfoLoginToken
    public R updateMethodInfo(@RequestBody MethodInfo methodInfo) {
        QueryWrapper<MethodInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", methodInfo.getId());
        Boolean u = methodInfoService.update(methodInfo,queryWrapper);
        if (!u) {
            return R.error("修改检测项目失败");
        }
        return R.ok();
    }

    /**
     *  删除检测项目
     * @param id
     * @return
     */
    @ApiOperation("删除检测项目")
    @GetMapping("/deleteMethodInfo")
    @UserinfoLoginToken
    public R deleteMethodInfo(Long id) {
        QueryWrapper<MethodInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        MethodInfo methodInfo = new MethodInfo();
        methodInfo.setStatus(StatusEnum.DELETE.getCode());
        //将实体对象进行包装，包装为操作条件
        Boolean pi = methodInfoService.update(methodInfo, queryWrapper);
        if (!pi) {
            return R.error("删除检测项目失败");
        }
        return R.ok("删除检测项目成功");
    }



    /**
     * 查询检测方法分类
     * @param methodId
     * @return
     */
    @ApiOperation("查询工程详细信息")
    @GetMapping(value = "/queryMethodInfo")
    @UserinfoLoginToken
    public R queryMethodInfo(Long methodId) {
        Map<String,Object> map = new HashMap<>();
        QueryWrapper<MethodInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("methodId",methodId);
        queryWrapper.eq("status",0);
        List<MethodInfo> methodInfo = methodInfoService.list(queryWrapper);
        map.put("methodInfo",methodInfo);
        return R.ok(map);
    }

    /**
     * 删除全部
     * @param
     * @return
     */
    @ApiOperation("删除全部")
    @GetMapping(value = "/deleteAllMethodInfo")
    @UserinfoLoginToken
    public R deleteAllMethodInfo() {
        QueryWrapper<MethodInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("methodId",-1);
        List<MethodInfo> methodInfo = methodInfoService.list(queryWrapper);
        for (MethodInfo info: methodInfo) {
//            info.setStatus(0);
            QueryWrapper<MethodInfo> queryWrapperOne = new QueryWrapper<>();
            queryWrapper.eq("methodId",info.getMethodId());
            methodInfoService.remove(queryWrapperOne);
        }
        return R.ok("删除检测项目成功");
    }

    /**
     * 查询检测方法分类
     *
     * @param
     * @return
     */
    @ApiOperation("查询检测方法分类")
    @GetMapping(value = "/queryByMethodInfoId")
    @UserinfoLoginToken
    public R queryByMethodInfoId() {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<MethodInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("methodId",-1);
        queryWrapper.eq("status",0);
        List<MethodInfo> methodInfo = methodInfoService.list(queryWrapper);
        map.put("methodInfo",methodInfo);
        return R.ok(map);
    }


    /**
     * 下载原始文件
     * @param path
     * @param response
     * @return
     */
    @ApiOperation("下载原始文件")
    @GetMapping("/downloadOriginFile")
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


    @ApiOperation("上传原始文件")
    @GetMapping("/uploadOriginFile")
    //requestParam要写才知道是前台的那个数组
    public R uploadOriginFile(@RequestParam("file") MultipartFile file) {
        // 保存文件
        String files =saveFile(file);
        if(files==null){
           return R.error("文件上传失败");
        }
        return R.ok("文件上传成功");//跳转的页面
    }

    private String saveFile(MultipartFile file) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                // 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中// )
//                String filePath = request.getSession().getServletContext()
//                        .getRealPath("/")
//                        + "upload/" + file.getOriginalFilename();
                Set<String> typeSet = new HashSet<>();
                typeSet.add(".zip");
                String originalFileName = file.getOriginalFilename();
                String suffix = originalFileName.substring(originalFileName.lastIndexOf('.'));
                if(!typeSet.contains(suffix)){
                    return null;
                }
                //2、使用UUID生成新文件名
                String newFileName = UUID.randomUUID() + suffix;

                String filePath = "C:\\data\\payment-dome\\src\\main\\webapps\\upload\\" + newFileName;
                File saveDir = new File(filePath);
                if (!saveDir.getParentFile().exists())
                    saveDir.getParentFile().mkdirs();
                // 转存文件
                file.transferTo(saveDir);
                return filePath;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}

