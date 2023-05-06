package com.yhgc.api.service.impl;

import com.yhgc.api.dto.MenuDto;
import com.yhgc.api.entity.Menu;
import com.yhgc.api.mapper.MenuMapper;
import com.yhgc.api.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 易生雄
 * @since 2023-04-24
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<MenuDto> menuList() {
        return menuMapper.menuList();
    }
}
