package com.yhgc.api.mapper;

import com.yhgc.api.entity.Access;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-30
 */
public interface AccessMapper extends BaseMapper<Access> {

    /**
     * 设置所有权限为0
     * @param roleId
     * @return
     */
    Boolean updateAccess(Integer roleId);

}
