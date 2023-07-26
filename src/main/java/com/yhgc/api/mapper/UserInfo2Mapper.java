package com.yhgc.api.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.dto.UserInfo2Dto;
import com.yhgc.api.entity.UserInfo2;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yhgc.api.entity.UserInfo2Vo;
import com.yhgc.api.vo.UserInfo3Vo;
import com.yhgc.api.vo.UserInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-26
 */
public interface UserInfo2Mapper extends BaseMapper<UserInfo2> {

    /**
     * 分页查询用户信息
     *
     * @return
     */
    IPage<UserInfo2Vo> pageQueryAllUserInfo(Page<UserInfo2Vo> page, @Param("query") String query,@Param("compId")String compId);

    /**
     *  根据名称查询用户
     */
    UserInfo3Vo getUserInfo(String account);

    /**
     * 查询所有用户名称
     * @return
     */
    List<UserInfoVo> queryAllName();


    /**
     *  根据ID查询用户
     * @param id
     * @return
     */
    UserInfo2Dto getByUserInfoId(Integer id);
}
