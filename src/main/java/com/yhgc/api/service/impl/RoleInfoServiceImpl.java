package com.yhgc.api.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.entity.RoleInfo;
import com.yhgc.api.mapper.RoleInfoMapper;
import com.yhgc.api.service.RoleInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yhgc.api.vo.RoleInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-26
 */
@Service
public class RoleInfoServiceImpl extends ServiceImpl<RoleInfoMapper, RoleInfo> implements RoleInfoService {

    @Resource
    private RoleInfoMapper roleInfoMapper;

    @Override
    public RoleInfo getOneRoleInfo(String name) {
        return roleInfoMapper.getOneRoleInfo(name);
    }

    @Override
    public List<RoleInfoVo> queryRoleName() {
        return roleInfoMapper.queryRoleName();
    }

    @Override
    public IPage<RoleInfo> pageQueryAllRoleInfo(Page<RoleInfo> page, String query,String compId) {
        return roleInfoMapper.pageQueryAllRoleInfo(page,query,compId);
    }
}
