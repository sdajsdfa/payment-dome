package com.yhgc.api.service.impl;

import com.yhgc.api.entity.Role;
import com.yhgc.api.mapper.RoleMapper;
import com.yhgc.api.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 易生雄
 * @since 2023-04-24
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public Role getOneRole(String name) {
        return roleMapper.getOneRole(name);
    }
}
