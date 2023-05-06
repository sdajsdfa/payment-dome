package com.yhgc.api.controller;


import cn.hutool.core.map.MapUtil;
import com.yhgc.api.dto.MenuDto;
import com.yhgc.api.entity.Menu;
import com.yhgc.api.entity.MenuInfo;
import com.yhgc.api.service.MenuService;
import com.yhgc.api.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2023-04-24
 */
@Api(tags = "菜单列表1")
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    /**
     *  查询菜单数据
     * @return
     */
    @ApiOperation("查询菜单数据")
    @GetMapping(value = "/queryAllMenu")
    public R queryAllMenuInfo() {
        Map<String,Object> map = new HashMap<>();
        // 模拟从数据库取数据
        List<MenuDto> rootMenu = menuService.menuList();
        List<MenuDto>  menuList = new ArrayList<>();
        for (MenuDto menu : rootMenu) {
            // 先找到所有根节点，根节点没有menuParentId
            if(menu.getParentId() == 0){
                // 以根节点为起点向下递归查找自己的子类
                menu = buildChildTree(menu , rootMenu);
                menuList.add(menu);
                map.put("menu",menuList);
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
    private static MenuDto buildChildTree(MenuDto menu, List<MenuDto> rootMenu) {
        // 用来存放自己的子节点
        List<MenuDto> childMenus = new ArrayList<>();
        for (MenuDto menuNode : rootMenu) {
            // 如果当前节点的id 与 菜单列表中其他节点的parentId相同，就讲其放入到childMenus中
            if(menuNode.getParentId()!=0 && menuNode.getParentId().equals(menu.getId())){
                // 递归查询子节点的child
                childMenus.add(buildChildTree(menuNode,rootMenu));
            }
        }
        // 每一个递归完成，给当前节点设置子节点
        menu.setChildMenus(childMenus);
        return menu;
    }

}

