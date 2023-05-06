package com.yhgc.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.dto.UserInfoDto;
import com.yhgc.api.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yhgc.api.vo.MethodInfoVo;
import com.yhgc.api.vo.UserInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    UserInfo getByUsername(String userName);

    UserInfoDto getUserById(Long id);

    List<UserInfoVo> queryAllName();

}
