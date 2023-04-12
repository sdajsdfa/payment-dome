package com.yhgc.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.entity.DeviceManagement;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yhgc.api.entity.ProjectInfo;
import com.yhgc.api.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 易生雄
 * @since 2023-03-27
 */
public interface DeviceManagementService extends IService<DeviceManagement> {

    IPage<DeviceManagement> searchPage(Page<DeviceManagement> page, @Param("query") String query);

    Boolean saveDeviceManagement (DeviceManagement deviceManagement);

}
