package com.yhgc.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.dto.UserInfoDto;
import com.yhgc.api.entity.UserInfo;
import com.yhgc.api.mapper.UserInfoMapper;
import com.yhgc.api.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yhgc.api.vo.UserInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-28
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public IPage<UserInfo> searchPage(Page<UserInfo> page, String query) {
        return userInfoMapper.searchPage(page,query);
    }

    @Override
    public UserInfo getByUsername(String userName) {
        return getOne(new QueryWrapper<UserInfo>().eq("userName", userName));
    }

    @Override
    public UserInfoDto getUserById(Long id) {
        return userInfoMapper.getUserById(id);
    }

    @Override
    public List<UserInfoVo> queryAllName() {
        return userInfoMapper.queryAllName();
    }

}
