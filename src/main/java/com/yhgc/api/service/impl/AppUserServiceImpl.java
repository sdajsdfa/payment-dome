package com.yhgc.api.service.impl;

import com.yhgc.api.entity.AppUser;
import com.yhgc.api.mapper.AppUserMapper;
import com.yhgc.api.service.AppUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 易生雄
 * @since 2022-12-21
 */
@Service
public class AppUserServiceImpl extends ServiceImpl<AppUserMapper, AppUser> implements AppUserService {

}
