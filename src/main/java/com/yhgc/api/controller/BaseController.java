package com.yhgc.api.controller;
import com.yhgc.api.service.*;
import com.yhgc.api.util.RedisUtil;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * 功能描述：基础控制器
 *
 */
public class BaseController {

    @Resource
    TestService testService;

    @Resource
    ProjectService projectService;

    @Resource
    MachineService machineService;

    @Resource
    VerificationService verificationService;

    @Resource
    CompanyInfoService companyInfoService;

    @Resource
    CompanyStructService companyStructService;

    @Resource
    UserInfo2Service userInfo2Service;

    @Resource
    RoleInfoService roleInfoService;

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    AccessService accessService;

    @Resource
    RedisUtil redisUtil;

    @Resource
    TenantService tenantService;

    @Resource
    DcBasicInfoService dcBasicinfoService;

    @Resource
    DcChannelService dcChannelService;

    @Resource
    SbBasicInfoService sbBasicInfoService;

    @Resource
    SbSectionService sbSectionService;

}
