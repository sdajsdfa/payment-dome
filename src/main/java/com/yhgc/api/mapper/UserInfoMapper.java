package com.yhgc.api.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.dto.UserInfoDto;
import com.yhgc.api.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yhgc.api.vo.UserInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户信息 Mapper 接口
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-28
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    IPage<UserInfo> searchPage(@Param("page") Page<UserInfo> page, @Param("query") String query);

    UserInfoDto getUserById(Long id);

    List<UserInfoVo> queryAllName();

}
