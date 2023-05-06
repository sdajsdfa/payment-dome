package com.yhgc.api.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.yhgc.api.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 易生雄
 * @since 2023-04-24
 */
public interface RoleService extends IService<Role> {

   Role getOneRole(String name);

}
