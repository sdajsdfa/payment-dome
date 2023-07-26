package com.yhgc.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.entity.Tenant;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yhgc.api.entity.Test;
import com.yhgc.api.vo.TestVo;
import org.apache.ibatis.annotations.Param;

import java.awt.*;
import java.util.List;

/**
 * <p>
 * 租户 服务类
 * </p>
 *
 * @author 易生雄
 * @since 2023-06-07
 */
public interface TenantService extends IService<Tenant> {


    /**
     *分页查询全部租户
     * @param page
     * @param query
     * @return
     */
     IPage<Tenant> pageQueryAllTenant(Page<Tenant> page, @Param("query") String query);


    List<Tenant> selectTenantCode(String compId);

}
