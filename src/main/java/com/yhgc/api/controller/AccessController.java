package com.yhgc.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yhgc.api.entity.Access;
import com.yhgc.api.entity.RoleInfo;
import com.yhgc.api.entity.UserInfo2;
import com.yhgc.api.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 权限 前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-30
 */
@Api(tags = "权限")
@RestController
@RequestMapping("/access")
public class AccessController extends BaseController {

    /**
     * 根据ID查询权限
     */
    @ApiOperation("根据ID查询权限")
    @GetMapping(value = "/queryAccess")
    public R queryAccess(Integer id) {
        Map<String, Object> map = new HashMap<>();
        UserInfo2 userInfo = userInfo2Service.getById(id);
        QueryWrapper<Access> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("RoleId",userInfo.getFromRoleId());
        Access access = accessService.getOne(queryWrapper);
        map.put("Access",access);
        return R.ok(map);
    }

}

