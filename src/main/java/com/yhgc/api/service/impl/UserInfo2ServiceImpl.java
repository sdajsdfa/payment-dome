package com.yhgc.api.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.dto.UserInfo2Dto;
import com.yhgc.api.entity.UserInfo2;
import com.yhgc.api.entity.UserInfo2Vo;
import com.yhgc.api.mapper.UserInfo2Mapper;
import com.yhgc.api.service.UserInfo2Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yhgc.api.vo.UserInfo3Vo;
import com.yhgc.api.vo.UserInfoVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-26
 */
@Service
public class UserInfo2ServiceImpl extends ServiceImpl<UserInfo2Mapper, UserInfo2> implements UserInfo2Service {

    @Resource
    private UserInfo2Mapper userInfo2Mapper;

    @Override
    public IPage<UserInfo2Vo> pageQueryAllUserInfo(Page<UserInfo2Vo> page, String query,String compId) {
        return userInfo2Mapper.pageQueryAllUserInfo(page,query,compId);
    }

    @Override
    public UserInfo3Vo getUserInfo(String account) {
        return userInfo2Mapper.getUserInfo(account);
    }

    @Override
    public List<UserInfoVo> queryAllName() {
        return userInfo2Mapper.queryAllName();
    }

    @Override
    public UserInfo2Dto getByUserInfoId(Integer id) {
        return userInfo2Mapper.getByUserInfoId(id);
    }
}
