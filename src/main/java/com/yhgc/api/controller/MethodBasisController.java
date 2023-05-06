package com.yhgc.api.controller;


import com.yhgc.api.dto.MenuDto;
import com.yhgc.api.entity.MethodBasis;
import com.yhgc.api.service.MethodBasisService;
import com.yhgc.api.util.R;
import com.yhgc.api.vo.UserInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
 * @since 2023-04-26
 */
@Api(tags = "检测方法表")
@RestController
@RequestMapping("/methodBasis")
public class MethodBasisController {

    @Resource
    private MethodBasisService methodBasisService;

    /**
     *查询检测方法
     */
    @ApiOperation("查询检测方法")
    @GetMapping(value = "/queryAllTestMethod")
    public R queryAllTestMethod() {
        Map<String,Object> map = new HashMap<>();
        List<MethodBasis> methodBases =methodBasisService.list();
        List<MethodBasis>  menuList = new ArrayList<>();
        for (MethodBasis menu : methodBases) {
            // 先找到所有根节点，根节点没有menuParentId
            if(menu.getParentId() == 0){
                // 以根节点为起点向下递归查找自己的子类
                menu = buildChildTree(menu , methodBases);
                menuList.add(menu);
                map.put("methodBasis",menuList);
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
    private static MethodBasis buildChildTree(MethodBasis menu, List<MethodBasis> rootMenu) {
        // 用来存放自己的子节点
        List<MethodBasis> childMenus = new ArrayList<>();
        for (MethodBasis menuNode : rootMenu) {
            // 如果当前节点的id 与 菜单列表中其他节点的parentId相同，就讲其放入到childMenus中
            if(menuNode.getParentId()!=0 && menuNode.getParentId().equals(menu.getId())){
                // 递归查询子节点的child
                childMenus.add(buildChildTree(menuNode,rootMenu));
            }
        }
        // 每一个递归完成，给当前节点设置子节点
        menu.setChildMethods(childMenus);
        return menu;
    }

}

