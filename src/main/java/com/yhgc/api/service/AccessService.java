package com.yhgc.api.service;

import com.yhgc.api.entity.Access;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-30
 */
public interface AccessService extends IService<Access> {

    /**
     * 设置所有权限为0
     * @param roleId
     * @return
     */
   Boolean updateAccess(Integer roleId);

}
