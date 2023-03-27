package com.yhgc.api.service;

import com.yhgc.api.entity.MenuInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 易生雄
 * @since 2023-03-10
 */
public interface MenuInfoService extends IService<MenuInfo> {

    List<MenuInfo> menuInfoList();

}
