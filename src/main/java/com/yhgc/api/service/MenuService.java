package com.yhgc.api.service;

import com.yhgc.api.dto.MenuDto;
import com.yhgc.api.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 易生雄
 * @since 2023-04-24
 */
public interface MenuService extends IService<Menu> {

   List<MenuDto> menuList();
}
