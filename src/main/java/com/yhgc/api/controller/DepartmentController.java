package com.yhgc.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yhgc.api.entity.Department;
import com.yhgc.api.entity.UnitInfo;
import com.yhgc.api.enums.StatusEnum;
import com.yhgc.api.service.DepartmentService;
import com.yhgc.api.service.UnitInfoService;
import com.yhgc.api.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 部门 前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-27
 */
@Api(tags = "部门")
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;

    @Resource
    private UnitInfoService unitinfoService;

    /**
     *查询部门
     * @return
     */
    @ApiOperation("查询部门")
    @GetMapping(value = "/queryAllSectorInfo")
    public R queryDpts(Integer unitId) {
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("unitId",unitId);
        queryWrapper.eq("status",0);
        List<Department> department = departmentService.list(queryWrapper);
        return R.ok(department);
    }

    /**
     *  修改单位信息
     * @param unitInfo
     *
     * @return
     */
    @ApiOperation("修改单位信息")
    @PostMapping(value = "/updateUnitInfo")
    public R updateUnitInfo(@RequestBody UnitInfo unitInfo) {
        unitInfo.setCreateTime(new Date());
        QueryWrapper<UnitInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", unitInfo.getId());
        Boolean si = unitinfoService.update(unitInfo,queryWrapper);
        if (!si) {
            return R.error("修改失败");
        }
        return R.ok(unitInfo);
    }

    /**
     *  添加部门
     * @param department
     * @return
     */
    @ApiOperation("添加部门")
    @PostMapping(value = "/addDpt")
    public R addDpt(@RequestBody Department department) {
        department.setCreateTime(new Date());
        department.setStatus(0);
        Boolean si = departmentService.save(department);
        if (!si) {
            return R.error("添加失败");
        }
        return R.ok(department);
    }

    /**
     *  修改部门信息
     * @param department
     * @return
     */
    @ApiOperation("修改部门信息")
    @PostMapping(value = "/updateDpt")
    public R updateDpt(@RequestBody Department department) {
        department.setCreateTime(new Date());
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", department.getId());
        Boolean dt = departmentService.update(department,queryWrapper);
        if (!dt) {
            return R.error("修改失败");
        }
        return R.ok(department);
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @ApiOperation("删除部门")
    @GetMapping(value = "/deleteDpt")
    public R deleteDpt(Long id) {
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        Department department = new Department();
        department.setStatus(StatusEnum.DELETE.getCode());
        //将实体对象进行包装，包装为操作条件
        Boolean ui =  departmentService.update(department,queryWrapper);
        if (!ui) {
            return R.error("删除部门失败");
        }
        return R.ok();
    }
}


