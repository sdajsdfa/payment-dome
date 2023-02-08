package com.yhgc.api.service.impl;

import com.yhgc.api.entity.UserInfo;
import com.yhgc.api.mapper.UserInfoMapper;
import com.yhgc.api.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
