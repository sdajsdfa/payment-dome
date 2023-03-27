package com.yhgc.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yhgc.api.vo.MethodInfoVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-28
 */
public interface UserInfoService extends IService<UserInfo> {

    IPage<UserInfo> searchPage(Page<UserInfo> page, @Param("query") String query);

}
