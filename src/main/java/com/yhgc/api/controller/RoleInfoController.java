package com.yhgc.api.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.entity.*;
import com.yhgc.api.service.UserinfoLoginToken;
import com.yhgc.api.util.R;
import com.yhgc.api.vo.RoleInfoVo;
import com.yhgc.api.vo.UserInfo3Vo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-26
 */
@Api(tags = "角色2")
@RestController
@RequestMapping("/roleInfo")
@Transactional
public class RoleInfoController extends BaseController {

    /**
     * 分页查询全部角色
     *
     * @return
     */
    @ApiOperation("分页查询全部角色")
    @GetMapping(value = "/pageQueryAllRoleInfo")
    @UserinfoLoginToken
    public R pageQueryAllRoleInfo(Integer pageNum, Integer pageSize, String query) {
        Map<String, Object> map = new HashMap<>();
        if(pageNum==null||pageSize==null){
            pageNum=1;
            pageSize=10;
        }
        Page<RoleInfo> page = new Page<>(pageNum, pageSize);
        UserInfo3Vo userInfo= (UserInfo3Vo) redisTemplate.opsForValue().get("userInfo");
        IPage<RoleInfo> IPage = roleInfoService.pageQueryAllRoleInfo(page, query,userInfo.getCompId());
        map.put("RoleInfo", IPage);
        return R.ok(map);
    }


    /**
     * 查询所有角色
     */
    @ApiOperation("查询所有角色名称")
    @GetMapping(value = "/queryRoleName")
    @UserinfoLoginToken
    public R queryRoleName() {
        Map<String, Object> map = new HashMap<>();
        List<RoleInfoVo> roleInfo = roleInfoService.queryRoleName();
        map.put("RoleInfo",roleInfo);
        return R.ok(map);
    }


    /**
     * 根据ID查询所有角色
     */
    @ApiOperation("根据ID查询所有角色")
    @GetMapping(value = "/queryRoleInfo")
    @UserinfoLoginToken
    public R queryRoleInfo(Integer id) {
        Map<String, Object> map = new HashMap<>();
        RoleInfo roleInfo = roleInfoService.getById(id);
//        QueryWrapper<Access> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("RoleId",roleInfo.getId());
//        Access access = accessService.getOne(queryWrapper);
        map.put("RoleInfo",roleInfo);
//        map.put("Access",access);
        return R.ok(map);
    }





    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    @ApiOperation("删除角色")
    @GetMapping(value = "/deleteRole")
    @UserinfoLoginToken
    public R deleteRoleInfo(Integer id) {
        //将实体对象进行包装，包装为操作条件
        Boolean pi = roleInfoService.removeById(id);
        if (!pi) {
            return R.error("删除角色失败");
        }
        return R.ok("删除角色成功");
    }

    /**
     * 添加和修改角色
     * @param roleInfo
     * @return
     */
    @ApiOperation(value = "添加和修改角色",httpMethod = "POST")
    @PostMapping("/addUpdateRoleInfo")
    @UserinfoLoginToken
    public R addUpdateRoleInfo(String roleInfo) {
        RoleInfo roleInfos = JSON.parseObject(roleInfo, RoleInfo.class);
        if(roleInfos.getId()<1) {
            try {
                QueryWrapper<RoleInfo> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("RoleName",roleInfos.getRoleName());
                RoleInfo roleInfoOne = roleInfoService.getOne(queryWrapper);
                if(roleInfoOne!=null){
                    return R.error("角色名称已经存在");
                }
                UserInfo3Vo userInfo= (UserInfo3Vo) redisTemplate.opsForValue().get("userInfo");
                roleInfos.setCompId(userInfo.getCompId());
                roleInfos.setCreateDate(new Date());
                roleInfos.setIsAdmin(0);
                Boolean ri =roleInfoService.save(roleInfos);
                if(!ri){
                    return R.error("添加失败");
                }
                Access access = JSON.parseObject(roleInfos.getRoleAccess(), Access.class);
                access.setRoleId(roleInfos.getId());
                System.out.println(access);
                Boolean a =accessService.save(access);
                return R.ok("添加成功");
            }catch (Exception e){
                return R.error(e.getMessage());
            }
        }else {
            try {
                Map<String, Object> map = new HashMap<>();
                roleInfoService.updateById(roleInfos);
                accessService.updateAccess(roleInfos.getId());
                QueryWrapper<Access> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("RoleId",roleInfos.getId());
                Access accessOne =accessService.getOne(queryWrapper);
                Access access = JSON.parseObject(roleInfos.getRoleAccess(), Access.class);
                access.setRoleId(accessOne.getRoleId());
                access.setId(accessOne.getId());
                accessService.updateById(access);

//                Access access1 = accessService.getById(roleInfos.getId());
//                map.put("Access",access1);
                UserInfo3Vo userInfo= (UserInfo3Vo) redisTemplate.opsForValue().get("userInfo");
                QueryWrapper<Access> queryWrapperOne = new QueryWrapper<>();
                queryWrapperOne.eq("RoleId",userInfo.getFromRoleId());
                Access as=accessService.getOne(queryWrapperOne);
                map.put("Access",as);
                return R.ok(map);
            }catch (Exception e){
                return R.error(e.getMessage());
            }
        }
    }
}

