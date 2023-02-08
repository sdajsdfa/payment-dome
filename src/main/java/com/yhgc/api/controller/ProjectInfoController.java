package com.yhgc.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yhgc.api.entity.ProjectInfo;
import com.yhgc.api.enums.StatusEnum;
import com.yhgc.api.service.ProjectFilesService;
import com.yhgc.api.service.ProjectInfoService;
import com.yhgc.api.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
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
@RequestMapping("/projectinfo")
public class ProjectInfoController {

    @Resource
    private ProjectInfoService projectinfoService;

    @Resource
    private ProjectFilesService projectfilesService;


    /**
     * 查询工程详细信息
     * @param id
     * @return
     */
    @ApiOperation("查询工程详细信息")
    @GetMapping(value = "/queryProjectInfo")
    public R queryProjectInfo(Long id) {
        ProjectInfo projectinfo = projectinfoService.getById(id);
        return R.ok(projectinfo);
    }

    /**
     * 查询工程及文件列表
     * @return
     */
    @ApiOperation("查询工程及文件列表")
    @GetMapping(value = "/queryProjectFileList")
    public R queryProjectFileList(Integer unitId) {
        return R.ok(projectfilesService.queryProjectFileList(unitId));
    }

    /**
     *  修改工程信息
     * @param projectinfo
     * @return
     */
    @ApiOperation("修改工程信息")
    @PostMapping(value = "/update")
    public R update(@RequestBody ProjectInfo projectinfo) {
        projectinfo.setCreateTime(new Date());
        projectinfo.setDeclareTime(new Date());
        QueryWrapper<ProjectInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", projectinfo.getId());
        queryWrapper.eq("status", 1);
        Boolean p = projectinfoService.update(projectinfo,queryWrapper);
        if (!p) {
            return R.error("修改失败");
        }
        return R.ok();
    }

    /**
     * 删除工程信息
     * @param id
     * @return
     */
    @ApiOperation("删除工程")
    @GetMapping  (value = "/deleteProject")
    public R deleteProject(Long id) {
        QueryWrapper<ProjectInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        ProjectInfo projectinfo = new ProjectInfo();
        projectinfo.setStatus(StatusEnum.DELETE.getCode());
        //将实体对象进行包装，包装为操作条件
        Boolean pi =  projectinfoService.update(projectinfo,queryWrapper);
        if (!pi) {
            return R.error("删除工程信息失败");
        }
        return R.ok();
    }

    /**
     * 申报工程
     * @param projectinfo
     * @return
     */
    @ApiOperation("申报工程")
    @PostMapping("/declareCheckProject")
    public R declareCheckProject(@RequestBody ProjectInfo projectinfo) {
        projectinfo.setDeclareTime(new Date());
        projectinfo.setCreateTime(new Date());
        projectinfo.setStatus(0);
        Boolean ui = projectinfoService.save(projectinfo);
        if (!ui) {
            return R.error("添加失败");
        }
        return R.ok(projectinfo);
    }
}

