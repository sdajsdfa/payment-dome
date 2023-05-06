package com.yhgc.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yhgc.api.dto.DepartmentDto;
import com.yhgc.api.entity.Department;
import com.yhgc.api.entity.UnitInfo;
import com.yhgc.api.service.DepartmentService;
import com.yhgc.api.service.UnitInfoService;
import com.yhgc.api.util.R;
import com.yhgc.api.vo.DepartmentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.*;

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
    @GetMapping(value = "/queryDpts")
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
     *  查询所有部门名称
     *
     * @return
     */
    @ApiOperation("查询所有部门名称")
    @PostMapping("/verificationAllDpt")
    public R verificationAllDpt() {
        Map<String, Object> map = new HashMap<>();
        List list =new ArrayList<>();
        QueryWrapper<DepartmentVo> queryAccount = new QueryWrapper<>();
        queryAccount.eq("status",0);
        List<DepartmentVo> departmentVos =  departmentService.verificationList();
        map.put("department",departmentVos);
        return R.ok(map);
    }

    /**
     * 查询根据Id查询
     */
    @ApiOperation("查询根据Id查询")
    @GetMapping(value = "/queryGetDpt")
    public R queryGetDpt(Long id) {
        Map<String,Object> map = new HashMap<>();
        DepartmentDto department = departmentService.getDptById(id);
        map.put("dtp",department);
        return R.ok(map);
    }


    /**
     *  查询所有部门名称
     * @return
     */
    @ApiOperation("查询所有部门名称")
    @GetMapping(value = "/queryAllDpt")
    public R queryAllDpt() {
        Map<String,Object> map = new HashMap<>();
        // 模拟从数据库取数据
        List<DepartmentDto> rootDpt = departmentService.dptList();
        List<DepartmentDto>  dptList = new ArrayList<>();
        for (DepartmentDto dpt : rootDpt) {
            // 先找到所有根节点，根节点没有menuParentId
            if(dpt.getParentId() == null){
                // 以根节点为起点向下递归查找自己的子类
                dpt = buildChildTree(dpt , rootDpt);
                dptList.add(dpt);
                map.put("dtp",dptList);
            }
        }
        return R.ok(map);
    }

    /**
     * 递归装载子类
     * @param dpt  当前节点
     * @param rootDpt 菜单列表
     * @return
     */
    private static DepartmentDto buildChildTree(DepartmentDto dpt, List<DepartmentDto> rootDpt) {
        // 用来存放自己的子节点
        List<DepartmentDto> childDpts = new ArrayList<>();
        for (DepartmentDto dptNode : rootDpt) {
            // 如果当前节点的id 与 菜单列表中其他节点的parentId相同，就讲其放入到childMenus中
            if(dptNode.getParentId()!=null && dptNode.getParentId().equals(dpt.getId())){
                // 递归查询子节点的child
                childDpts.add(buildChildTree(dptNode,rootDpt));
            }
        }
        // 每一个递归完成，给当前节点设置子节点
        dpt.setChildDpts(childDpts);
        return dpt;
    }

    /**
     *  添加和修改部门信息
     * @param departmentDto
     * @return
     */
    @ApiOperation("添加和修改部门信息")
    @PostMapping(value = "/saveAndUpdateDpts")
    public R updateDpts(@RequestBody DepartmentDto departmentDto) {
        if(departmentDto.getId()<1){
            Department dt =departmentService.getById(departmentDto.getParentId());
            if(dt==null){
                return R.error("不可以添加");
            }
            departmentDto.setCreateTime(new Date());
            QueryWrapper<Department> query = new QueryWrapper<>();
            query.eq("dptName",departmentDto.getDptName());
            Department department = departmentService.getOne(query);
            if(department!=null){
                return R.error("已存在名为"+departmentDto .getDptName()+"的组织.无法在一级别创建相同名称的组织单位.");
            }
            Boolean si = departmentService.saveDpt(departmentDto);
            if (!si) {
                return R.error("添加失败");
            }
            return R.ok("添加成功!");
        }else {
            System.out.println(departmentDto);
            Boolean dt = departmentService.updateDepartment(departmentDto);
            if (!dt) {
                return R.error("更新失败");
            }
            return R.ok("更新成功!");
        }
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @ApiOperation("删除部门")
    @GetMapping(value = "/deleteDpt")
    public R deleteDpt(Long id) {
        //将实体对象进行包装，包装为操作条件
        Boolean ui =  departmentService.removeById(id);
        if (!ui) {
            return R.error("删除部门失败!");
        }
        return R.ok("删除部门成功");
    }


}


