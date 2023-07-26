package com.yhgc.api.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.entity.Tenant;
import com.yhgc.api.mapper.TenantMapper;
import com.yhgc.api.service.TenantService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 租户 服务实现类
 * </p>
 *
 * @author 易生雄
 * @since 2023-06-07
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements TenantService {

    @Resource
    private TenantMapper tenantMapper;

    @Override
    public IPage<Tenant> pageQueryAllTenant(Page<Tenant> page, String query) {
        return tenantMapper.pageQueryAllTenant(page,query);
    }

    @Override
    public List<Tenant> selectTenantCode(String compId) {
        return tenantMapper.selectTenantCode(compId);
    }
}
