package com.yhgc.api.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.dto.ProjectInfoDto;
import com.yhgc.api.entity.MethodInfo;
import com.yhgc.api.entity.PileParams;
import com.yhgc.api.entity.ProjectInfo;
import com.yhgc.api.entity.Units;
import com.yhgc.api.service.*;
import com.yhgc.api.util.R;
import com.yhgc.api.vo.MethodInfoVo;
import com.yhgc.api.vo.ProjectInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
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
    private ProjectInfoService projectInfoService;

    @Resource
    private PileParamsService pileParamsService;

    @Resource UnitsService unitsService;

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
//    @UserinfoLoginToken
    public R queryProjectInfo(Long id) {
        Map<String, Object> map = new HashMap<>();
        ProjectInfo projectInfo = projectInfoService.getById(id);
        if(projectInfo==null){
            return R.ok();
        }
        QueryWrapper<PileParams> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("projectId",projectInfo.getId());
        PileParams pileParams =pileParamsService.getOne(queryWrapper);
        QueryWrapper<Units> unitsQueryWrapper = new QueryWrapper<>();
        unitsQueryWrapper.eq("projectId",projectInfo.getId());
        Units units = unitsService.getOne(unitsQueryWrapper);
        projectInfo.setPileParams(pileParams);
        projectInfo.setUnits(units);
        map.put("project",projectInfo);
        QueryWrapper<MethodInfo> methodInfoQueryWrapper = new QueryWrapper<>();
        methodInfoQueryWrapper.eq("methodId",projectInfo.getId());
        List<MethodInfo> methodInfos = methodInfoService.list(methodInfoQueryWrapper);
        map.put("entrustList",methodInfos);
        return R.ok(map);
    }


    /**
     * 查询工程详细信息
     *
     * @return
     */
    @ApiOperation("查询工程全部信息")
    @GetMapping(value = "/queryAllProjectInfo")
//    @UserinfoLoginToken
    public R queryAllProjectInfo(Integer pageNum, Integer pageSize, String query) {
        Map<String, Object> map = new HashMap<>();
        if(pageNum==null||pageSize==null){
            pageNum=1;
            pageSize=10;
        }
        Page<ProjectInfoDto> page = new Page<>(pageNum, pageSize);
        IPage<ProjectInfoDto> iPage = projectInfoService.searchPage(page,query);
        map.put("projectInfo", iPage);

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
     * @param projectInfo
     * @return
     */
    @ApiOperation(value = "添加和修改工程信息",httpMethod = "POST")
    @PostMapping (value = "/addUpdateProjectInfo")
//    @UserinfoLoginToken
    public R addUpdateProjectInfo(String projectInfo,String methodInfo,@RequestParam("files") MultipartFile[] files,@RequestParam("photograph") MultipartFile[] photograph,HttpServletRequest request) {
        ProjectInfo projectInfos =JSON.parseObject(projectInfo,ProjectInfo.class);
        List<MethodInfo> methodInfos = JSON.parseArray(methodInfo,MethodInfo.class);
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
        List list2 = new ArrayList();
        if (files != null && photograph.length > 0) {
            for (int i = 0; i < photograph.length; i++) {
                MultipartFile file = photograph[i];
                // 保存文件
                list2 = saveFile(request, file, list);
                if(list2==null){
                    return R.error("上传类型错误");
                }
            }
        }
        projectInfos.setPicture(StringUtils.join(Arrays.asList(list.toArray()),", "));
        projectInfos.setPhotograph(StringUtils.join(Arrays.asList(list2.toArray()),", "));
        if(projectInfos.getId()<0){
            QueryWrapper<ProjectInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("projectName",projectInfos.getProjectName());
            ProjectInfo projectInfoOne = projectInfoService.getOne(queryWrapper);
            if(projectInfoOne!=null){
                return R.error("该工程名称已经存在！");
            }
            projectInfos.setCreationTime(new Date());
            Boolean ui = projectInfoService.save(projectInfos);
            if (!ui) {
                return R.error("添加工程失败");
            }

            if(projectInfos.getPileParams()!=null){
                projectInfos.getPileParams().setProjectId(projectInfos.getId());
                pileParamsService.save(projectInfos.getPileParams());
            }
            if( projectInfos.getUnits()!=null) {
                projectInfos.getUnits().setProjectId(projectInfos.getId());
                unitsService.save(projectInfos.getUnits());
            }
            if(methodInfos!=null){
                for (MethodInfo info: methodInfos) {
                    info.setMethodId(Math.toIntExact(projectInfos.getId()));
                }
                methodInfoService.saveBatch(methodInfos);
            }
            return R.ok("添加工程成功");
        }else {
            Boolean p = projectInfoService.updateById(projectInfos);
            pileParamsService.updateById(projectInfos.getPileParams());
            unitsService.updateById(projectInfos.getUnits());
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

        //将实体对象进行包装，包装为操作条件
        Boolean pi = projectInfoService.removeById(id);

        QueryWrapper<PileParams> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("projectId",id);
        pileParamsService.remove(queryWrapper);

        QueryWrapper<Units> unitsQueryWrapper = new QueryWrapper<>();
        unitsQueryWrapper.eq("projectId",id);
        unitsService.remove(unitsQueryWrapper);

//        QueryWrapper<MethodInfo> methodInfoQueryWrapper = new QueryWrapper<>();
//        methodInfoQueryWrapper.eq("methodId",id);
//        unitsService.remove(unitsQueryWrapper);
        if (!pi) {
            return R.error("删除工程信息失败");
        }
        return R.ok();
    }
//
//    /**
//     * 申报工程
//     *
//     * @param projectinfo
//     * @return
//     */
//    @ApiOperation("申报工程")
//    @PostMapping("/declareCheckProject")
//    @UserinfoLoginToken
//    public R declareCheckProject(ProjectInfo projectinfo) {
////        System.out.println(projectinfo.getFiles().length+"-------------");
//        projectinfo.setDeclareTime(new Date());
//        projectinfo.setCreateTime(new Date());
//        projectinfo.setStatus(0);
//        Boolean ui = projectinfoService.saveProject(projectinfo);
//        if (!ui) {
//            return R.error("添加失败");
//        }
//        return R.ok(projectinfo);
//    }
//
//    @ApiOperation("图片上传")
//    @GetMapping("/filesUpload")
//    @UserinfoLoginToken
//    //requestParam要写才知道是前台的那个数组
//    public String filesUpload(@RequestParam("myfiles") MultipartFile[] files, HttpServletRequest request) {
//        List list = new ArrayList();
//        System.out.println(files.length+"-------------");
//        if (files != null && files.length > 0) {
//            for (int i = 0; i < files.length; i++) {
//                MultipartFile file = files[i];
//                // 保存文件
//                list = saveFile(request, file, list);
//            }
//        }
//        ProjectInfo projectInfo =new ProjectInfo();
//        projectInfo.setPicture(StringUtils.join(Arrays.asList(list.toArray()),", "));
//        System.out.println(projectInfo);
//       //写着测试，删了就可以
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println("集合里面的数据" + list.get(i));
//        }
//        return StringUtils.join(Arrays.asList(list.toArray()),", ");//跳转的页面
//    }
//
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
        ProjectInfo qa =  projectInfoService.getOne(queryAccount);
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
        List<ProjectInfoVo> projectInfoVos =  projectInfoService.verificationList();
        map.put("projectInfo",projectInfoVos);
        return R.ok(map);
    }

}

