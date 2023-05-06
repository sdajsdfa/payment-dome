package com.yhgc.api.mapper;

import com.yhgc.api.dto.MenuDto;
import com.yhgc.api.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 易生雄
 * @since 2023-04-24
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<MenuDto> menuList();
}
