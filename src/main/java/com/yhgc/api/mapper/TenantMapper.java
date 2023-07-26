package com.yhgc.api.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.entity.Tenant;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 租户 Mapper 接口
 * </p>
 *
 * @author 易生雄
 * @since 2023-06-07
 */
public interface TenantMapper extends BaseMapper<Tenant> {

    /**
     *分页查询全部租户
     * @param page
     * @param query
     * @return
     */
    IPage<Tenant> pageQueryAllTenant(Page<Tenant> page, @Param("query") String query);


    List<Tenant> selectTenantCode(String compId);


}
