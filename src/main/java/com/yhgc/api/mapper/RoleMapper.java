package com.yhgc.api.mapper;

import com.yhgc.api.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 易生雄
 * @since 2023-04-24
 */
public interface RoleMapper extends BaseMapper<Role> {

    Role getOneRole(@Param("name") String name);
}
