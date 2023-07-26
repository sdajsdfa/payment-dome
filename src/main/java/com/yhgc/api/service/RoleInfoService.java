package com.yhgc.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.entity.RoleInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yhgc.api.vo.RoleInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-26
 */
public interface RoleInfoService extends IService<RoleInfo> {

    RoleInfo getOneRoleInfo(String name);


    /**
     * 查询所有角色
     */
    List<RoleInfoVo> queryRoleName();

    /**
     *分页查询全部角色
     * @param page
     * @param query
     * @return
     */
    IPage<RoleInfo> pageQueryAllRoleInfo(Page<RoleInfo> page, @Param("query") String query,@Param("compId")String compId);


}
