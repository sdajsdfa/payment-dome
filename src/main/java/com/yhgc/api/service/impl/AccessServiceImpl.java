package com.yhgc.api.service.impl;

import com.yhgc.api.entity.Access;
import com.yhgc.api.mapper.AccessMapper;
import com.yhgc.api.service.AccessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-30
 */
@Service
public class AccessServiceImpl extends ServiceImpl<AccessMapper, Access> implements AccessService {

    @Resource
    private AccessMapper accessMapper;

    @Override
    public Boolean updateAccess(Integer roleId) {
        return accessMapper.updateAccess(roleId);
    }
}
