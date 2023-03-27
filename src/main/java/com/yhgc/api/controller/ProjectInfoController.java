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
import com.yhgc.api.vo.ProjectInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 * <p>
 * 工程信息 前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-27
 */
@Api(tags = "工程信息")
@RestController
@RequestMapping("/projectInfo")
@Transactional
public class ProjectInfoController {

    @Resource
    private ProjectInfoService projectinfoService;

    @Resource
    private ProjectFilesService projectfilesService;

    @Resource
    private MethodInfoService methodInfoService;


    /**
     * 查询工程详细信息
     *
     * @param id
     * @return
     */
    @ApiOperation("查询工程详细信息")
    @GetMapping(value = "/queryProjectInfo")
    @UserinfoLoginToken
    public R queryProjectInfo(Long id) {
        Map<String, Object> map = new HashMap<>();
        ProjectInfo projectInfo = projectinfoService.getById(id);
        map.put("projectDetailed",projectInfo);
        return R.ok(map);
    }


    /**
     * 查询工程详细信息
     *
     * @return
     */
    @ApiOperation("查询工程全部信息")
    @GetMapping(value = "/queryAllProjectInfo")
    @UserinfoLoginToken
    public R queryAllProjectInfo(Integer pageNum, Integer pageSize, String query) {
        Map<String, Object> map = new HashMap<>();
        ProjectInfo projectInfo = new ProjectInfo();
        if(pageNum==null||pageSize==null){
            pageNum=1;
            pageSize=10;
        }
        Page<ProjectInfo> page = new Page<>(pageNum, pageSize);
        if (!(query.equals(""))) {
            if (ProjectInfoController.isNumeric(query)) {
                projectInfo.setId(Long.valueOf(query));
            } else {
                projectInfo.setProjectName(query);
            }
        } else {
            projectInfo.setProjectName("");
        }
        QueryWrapper<ProjectInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 0);
        queryWrapper.like("projectName", projectInfo.getProjectName()).or().eq("id", projectInfo.getId());
        IPage<ProjectInfo> projectinfo = projectinfoService.page(page, queryWrapper);
        map.put("projectInfo", projectinfo);
        return R.ok(map);
    }

    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            int chr = str.charAt(i);
            if (chr < 48 || chr > 57)
                return false;
        }
        return true;
    }


    /**
     * 查询工程及文件列表
     *
     * @return
     */
    @ApiOperation("查询工程及文件列表")
    @GetMapping(value = "/queryProjectFileList")
    @UserinfoLoginToken
    public R queryProjectFileList(Integer unitId) {
        return R.ok(projectfilesService.queryProjectFileList(unitId));
    }

    /**
     * 添加和修改工程信息
     *
     * @param projectinfo
     * @return
     */
    @ApiOperation(value = "添加和修改工程信息",httpMethod = "POST")
    @PostMapping (value = "/addUpdateProjectInfo")
    @UserinfoLoginToken
    public R addUpdateProjectInfo(ProjectInfo projectinfo,@RequestParam("files")MultipartFile[] files, HttpServletRequest request) {
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
        projectinfo.setPicture(StringUtils.join(Arrays.asList(list.toArray()),", "));
        if(projectinfo.getId()<0){
            projectinfo.setDeclareTime(new Date());
            projectinfo.setCreateTime(new Date());
            projectinfo.setStatus(0);
            Boolean ui = projectinfoService.saveProject(projectinfo);

            System.out.println(projectinfo.getId()+"===============================");
            if (!ui) {
                return R.error("添加工程失败");
            }
            QueryWrapper<MethodInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("methodId",-1);
            List<MethodInfo> methodInfo = methodInfoService.list(queryWrapper);
            for (MethodInfo info: methodInfo) {
                info.setMethodId(Math.toIntExact(projectinfo.getId()));
                methodInfoService.updateById(info);
            }
            return R.ok("添加工程成功");
        }else {
//            projectinfo.setCreateTime(new Date());
//            projectinfo.setDeclareTime(new Date());
//            QueryWrapper<ProjectInfo> queryWrapper = new QueryWrapper<>();
//            queryWrapper.eq("id", projectinfo.getId());
//            queryWrapper.eq("status", 1);
            Boolean p = projectinfoService.updateProject(projectinfo);
            if (!p) {
                return R.error("修改工程失败");
            }
            return R.ok("修改工程成功");
        }
    }

    /**
     * 删除工程信息
     *
     * @param id
     * @return
     */
    @ApiOperation("删除工程")
    @GetMapping(value = "/deleteProject")
    @UserinfoLoginToken
    public R deleteProject(Long id) {
        QueryWrapper<ProjectInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        ProjectInfo projectinfo = new ProjectInfo();
        projectinfo.setStatus(StatusEnum.DELETE.getCode());
        //将实体对象进行包装，包装为操作条件
        Boolean pi = projectinfoService.update(projectinfo, queryWrapper);
        if (!pi) {
            return R.error("删除工程信息失败");
        }
        return R.ok();
    }

    /**
     * 申报工程
     *
     * @param projectinfo
     * @return
     */
    @ApiOperation("申报工程")
    @PostMapping("/declareCheckProject")
    @UserinfoLoginToken
    public R declareCheckProject(ProjectInfo projectinfo) {
//        System.out.println(projectinfo.getFiles().length+"-------------");
        projectinfo.setDeclareTime(new Date());
        projectinfo.setCreateTime(new Date());
        projectinfo.setStatus(0);
        Boolean ui = projectinfoService.saveProject(projectinfo);
        if (!ui) {
            return R.error("添加失败");
        }
        return R.ok(projectinfo);
    }

    @ApiOperation("图片上传")
    @GetMapping("/filesUpload")
    @UserinfoLoginToken
    //requestParam要写才知道是前台的那个数组
    public String filesUpload(@RequestParam("myfiles") MultipartFile[] files, HttpServletRequest request) {
        List list = new ArrayList();
        System.out.println(files.length+"-------------");
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                // 保存文件
                list = saveFile(request, file, list);
            }
        }
        ProjectInfo projectInfo =new ProjectInfo();
        projectInfo.setPicture(StringUtils.join(Arrays.asList(list.toArray()),", "));
        System.out.println(projectInfo);
       //写着测试，删了就可以
        for (int i = 0; i < list.size(); i++) {
            System.out.println("集合里面的数据" + list.get(i));
        }
        return StringUtils.join(Arrays.asList(list.toArray()),", ");//跳转的页面
    }

    private List saveFile(HttpServletRequest request, MultipartFile file, List list) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                // 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中// )
//                String filePath = request.getSession().getServletContext()
//                        .getRealPath("/")
//                        + "upload/" + file.getOriginalFilename();
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
     * 工程名称校验
     *
     * @param projectName
     * @return
     */
    @ApiOperation("工程名称校验")
    @GetMapping("/verificationProjectName")
    @UserinfoLoginToken
    public R verificationProjectName(String projectName) {
        QueryWrapper<ProjectInfo> queryAccount = new QueryWrapper<>();
        queryAccount.eq("projectName",projectName);
        ProjectInfo qa =  projectinfoService.getOne(queryAccount);
        if (qa != null){
            return R.error("工程名称已经存在");
        }
        return R.ok();
    }

    /**
     *  查询所有工程名称
     *
     * @return
     */
    @ApiOperation("工程名称")
    @PostMapping("/verificationAllProjectName")
    public R verificationAllProjectName() {
        Map<String, Object> map = new HashMap<>();
//        List list =new ArrayList<>();
//        QueryWrapper<ProjectInfo> queryAccount = new QueryWrapper<>();
//        queryAccount.eq("status",0);
        List<ProjectInfoVo> projectInfoVos =  projectinfoService.verificationList();
        map.put("projectInfo",projectInfoVos);
        return R.ok(map);
    }

}

