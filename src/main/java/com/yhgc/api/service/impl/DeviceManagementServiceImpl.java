package com.yhgc.api.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.entity.DeviceManagement;
import com.yhgc.api.mapper.DeviceManagementMapper;
import com.yhgc.api.service.DeviceManagementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 易生雄
 * @since 2023-03-27
 */
@Service
public class DeviceManagementServiceImpl extends ServiceImpl<DeviceManagementMapper, DeviceManagement> implements DeviceManagementService {

    @Resource
    private DeviceManagementMapper deviceManagementMapper;

    @Override
    public IPage<DeviceManagement> searchPage(Page<DeviceManagement> page, String query) {
        return deviceManagementMapper.searchPage(page,query);
    }

    @Override
    public Boolean saveDeviceManagement(DeviceManagement deviceManagement) {
        return deviceManagementMapper.saveDeviceManagement(deviceManagement);
    }
}
