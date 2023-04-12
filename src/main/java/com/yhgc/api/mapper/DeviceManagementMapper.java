package com.yhgc.api.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.entity.DeviceManagement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 易生雄
 * @since 2023-03-27
 */
public interface DeviceManagementMapper extends BaseMapper<DeviceManagement> {

    IPage<DeviceManagement> searchPage(@Param("page") Page<DeviceManagement> page, @Param("query") String query);

    Boolean saveDeviceManagement (DeviceManagement deviceManagement);
}
