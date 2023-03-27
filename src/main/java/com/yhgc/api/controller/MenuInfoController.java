package com.yhgc.api.controller;


import com.yhgc.api.entity.DataInfo;
import com.yhgc.api.entity.MenuInfo;
import com.yhgc.api.service.MenuInfoService;
import com.yhgc.api.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2023-03-10
 */
@Api(tags = "菜单列表")
@RestController
@RequestMapping("/menuInfo")
public class MenuInfoController {


    @Resource
    private MenuInfoService menuInfoService;


    /**
     *  查询菜单数据
     * @return
     */
    @ApiOperation("查询菜单数据")
    @PostMapping(value = "/queryAllMenuInfo")
    public R queryAllMenuInfo() {
        Map<String,Object> map = new HashMap<>();
        // 模拟从数据库取数据
        List<MenuInfo> rootMenu = menuInfoService.menuInfoList();
        List<MenuInfo>  menuList = new ArrayList<>();
        for (MenuInfo menu : rootMenu) {
            // 先找到所有根节点，根节点没有menuParentId
            if(menu.getMenuParentId() == null){
                // 以根节点为起点向下递归查找自己的子类
                menu = buildChildTree(menu , rootMenu);
                menuList.add(menu);
                map.put("menuInfo",menuList);
            }
        }
        return R.ok(map);
    }

    /**
     * 递归装载子类
     * @param menu  当前节点
     * @param rootMenu 菜单列表
     * @return
     */
    private static MenuInfo buildChildTree(MenuInfo menu, List<MenuInfo> rootMenu) {
        // 用来存放自己的子节点
        List<MenuInfo> childMenus = new ArrayList<>();
        for (MenuInfo menuNode : rootMenu) {
            // 如果当前节点的id 与 菜单列表中其他节点的parentId相同，就讲其放入到childMenus中
            if(menuNode.getMenuParentId()!=null && menuNode.getMenuParentId().equals(menu.getId())){
                // 递归查询子节点的child
                childMenus.add(buildChildTree(menuNode,rootMenu));
            }
        }
        // 每一个递归完成，给当前节点设置子节点
        menu.setChildMenus(childMenus);
        return menu;
    }

}

