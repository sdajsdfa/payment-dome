package com.yhgc.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.entity.ProjectInfo;
import com.yhgc.api.entity.Role;
import com.yhgc.api.entity.RoleMenu;
import com.yhgc.api.entity.UserInfo;
import com.yhgc.api.service.RoleMenuService;
import com.yhgc.api.service.RoleService;
import com.yhgc.api.service.UserInfoService;
import com.yhgc.api.service.UserinfoLoginToken;
import com.yhgc.api.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2023-04-24
 */
@Api(tags = "角色")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @Resource
    private RoleMenuService roleMenuService;


    @Resource
    private UserInfoService userInfoService;

    /**
     * 查询角色
     *
     * @return
     */
    @ApiOperation("查询角色")
    @GetMapping(value = "/queryAllRole")
    public R queryAllRole(Integer pageNum, Integer pageSize, String query) {
        System.out.println(query+"============");
        Map<String, Object> map = new HashMap<>();
        Role role = new Role();
        if(pageNum==null||pageSize==null){
            pageNum=1;
            pageSize=10;
        }
        Page<Role> page = new Page<>(pageNum, pageSize);
        if(query==null){
            role.setName("");
        }else {
            role.setName(query);
        }
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", role.getName());
        IPage<Role> userRoleIPage = roleService.page(page, queryWrapper);
        map.put("userRole", userRoleIPage);
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
    public R deleteRole(Long id) {

        //将实体对象进行包装，包装为操作条件
        Boolean pi = roleService.removeById(id);
        if (!pi) {
            return R.error("删除角色失败");
        }
        return R.ok("删除角色成功");
    }

    /**
     * 添加和修改角色
     * @param role
     * @return
     */
    @ApiOperation("添加和修改角色")
    @PostMapping("/saveAndUpdateRole")
    public R saveAndUpdateRole(@RequestBody Role role) {
        System.out.println(role+"==============");
        List<RoleMenu> roleMenus = new ArrayList<>();
        Role roleOne = roleService.getOneRole(role.getName());
        if(roleOne!=null){
            R.error("角色名称已经存在");
        }
        if(role.getId()<1) {
            role.setCreationTime(new Date());
            role.setIsStatic(false);
            role.setIsDefault(false);
            role.setDisplayName(role.getName());
            Boolean r = roleService.save(role);
            if (!r) {
                return R.error("添加角色失败");
            }
//            List<RoleMenu> roleMenus = new ArrayList<>();
            Arrays.stream(role.getMenuIds().toArray(new Long[0])).forEach(menuId -> {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setMenuId(menuId);
                roleMenu.setRoleId(role.getId());
                roleMenus.add(roleMenu);
            });

            System.out.println(roleMenus + "============");
            // 先删除原来的记录，再保存新的
            roleMenuService.remove(new QueryWrapper<RoleMenu>().eq("role_id", role.getId()));
            roleMenuService.saveBatch(roleMenus);

            // 删除缓存
//        userInfoService.clearUserAuthorityInfoByRoleId(role.getId());
//        return Result.succ(menuIds);

            return R.ok(role);
        }else {
            role.setCreationTime(new Date());
            roleService.updateById(role);
            Arrays.stream(role.getMenuIds().toArray(new Long[0])).forEach(menuId -> {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setMenuId(menuId);
                roleMenu.setRoleId(role.getId());
                roleMenus.add(roleMenu);
            });
            // 先删除原来的记录，再保存新的
            roleMenuService.remove(new QueryWrapper<RoleMenu>().eq("role_id", role.getId()));
            roleMenuService.saveBatch(roleMenus);
            // 更新缓存
//            sysUserService.clearUserAuthorityInfoByRoleId(sysRole.getId());
            return R.ok("修改成功");
        }
    }


    /**
     * 角色查询
     *
     * @return
     */
    @ApiOperation("根据ID查询角色")
    @GetMapping(value = "/queryRole")
    public R queryRole(Long id) {
        Map<String,Object> map = new HashMap<>();
        Role roles = roleService.getById(id);
        // 获取角色相关联的菜单id
        List<RoleMenu> roleMenus = roleMenuService.list(new QueryWrapper<RoleMenu>().eq("role_id", id));
        List<Long> menuIds = roleMenus.stream().map(p -> p.getMenuId()).collect(Collectors.toList());
        roles.setMenuIds(menuIds);
        map.put("role",roles);
        return R.ok(map);
    }


}

