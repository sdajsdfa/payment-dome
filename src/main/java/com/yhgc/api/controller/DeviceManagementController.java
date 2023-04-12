package com.yhgc.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.deploy.util.StringUtils;
import com.yhgc.api.entity.DeviceManagement;
import com.yhgc.api.entity.MethodInfo;
import com.yhgc.api.entity.ProjectInfo;
import com.yhgc.api.entity.UserInfo;
import com.yhgc.api.service.DeviceManagementService;
import com.yhgc.api.service.UserinfoLoginToken;
import com.yhgc.api.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2023-03-27
 */
@Api(tags = "设备管理")
@RestController
@RequestMapping("/deviceManagement")
public class DeviceManagementController {

    @Resource
    private DeviceManagementService deviceManagementService;


    /**
     * 根据id查询设备管理
     *
     * @param id
     * @return
     */
    @ApiOperation("根据id查询设备管理")
    @GetMapping(value = "/queryDeviceManagement")
    public R queryDeviceManagement(Long id) {
        Map<String, Object> map = new HashMap<>();
        DeviceManagement deviceManagement = deviceManagementService.getById(id);
        map.put("deviceManagementDetailed",deviceManagement);
        return R.ok(map);
    }


    /**
     * 查询设备管理
     *
     * @return
     */
    @ApiOperation("查询设备管理")
    @GetMapping(value = "/queryAllDeviceManagement")
    public R queryAllDeviceManagement(Integer pageNum, Integer pageSize, String query) {
        Map<String, Object> map = new HashMap<>();
        if(pageNum==null||pageSize==null){
            pageNum=1;
            pageSize=10;
        }
        Page<DeviceManagement> page = new Page<>(pageNum, pageSize);
        IPage<DeviceManagement> iPage = deviceManagementService.searchPage(page,query);
        map.put("deviceManagement", iPage);
        return R.ok(map);
    }


    /**
     * 添加和修改设备管理
     *
     * @param deviceManagement
     * @return
     */
    @ApiOperation(value = "添加和修改设备管理",httpMethod = "POST")
    @PostMapping(value = "/addUpdateDeviceManagement")
    public R addUpdateDeviceManagement(@RequestBody DeviceManagement deviceManagement) {
        Map<String,Object> map = new HashMap<>();
        if(deviceManagement.getId()<0){
            deviceManagement.setCreationTime(new Date());
            deviceManagement.setCcDate(new Date());
            deviceManagement.setGrTime(new Date());
            Boolean ui = deviceManagementService.saveDeviceManagement(deviceManagement);
            if (ui) {
//                map.put("deviceManagement",deviceManagementService.getById(deviceManagement.getId()));
                return R.ok(map);
            }
            return R.error("添加设备管理失败");
        }else {
            QueryWrapper<DeviceManagement> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", deviceManagement.getId());
            Boolean p = deviceManagementService.update(deviceManagement,queryWrapper);
            if (!p) {
                return R.error("修改设备管理失败");
            }
            return R.ok("修改设备管理成功");

        }
    }



    /**
     * 删除人员
     * @param id
     * @return
     */
    @ApiOperation("删除人员")
    @GetMapping(value = "/invalidDeviceManagement")
    public R invalidUser(Long id) {
        //将实体对象进行包装，包装为操作条件
        Boolean pi = deviceManagementService.removeById(id);
        if (!pi) {
            return R.error("删除设备管理失败");
        }
        return R.ok("删除设备管理成功");
    }

}

