package com.yhgc.api.mapper;

import com.yhgc.api.entity.MenuInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 易生雄
 * @since 2023-03-10
 */
public interface MenuInfoMapper extends BaseMapper<MenuInfo> {
    List<MenuInfo> menuInfoList();
}
