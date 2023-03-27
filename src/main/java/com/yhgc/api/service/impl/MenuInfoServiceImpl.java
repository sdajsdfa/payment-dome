package com.yhgc.api.service.impl;

import com.yhgc.api.controller.MenuInfoController;
import com.yhgc.api.entity.MenuInfo;
import com.yhgc.api.mapper.MenuInfoMapper;
import com.yhgc.api.service.MenuInfoService;
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
 * @since 2023-03-10
 */
@Service
public class MenuInfoServiceImpl extends ServiceImpl<MenuInfoMapper, MenuInfo> implements MenuInfoService {

    @Resource
    private MenuInfoMapper menuInfoMapper;

    @Override
    public List<MenuInfo> menuInfoList() {
        return menuInfoMapper.menuInfoList();
    }
}
