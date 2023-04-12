package com.yhgc.api.service.impl;

import com.yhgc.api.entity.Verification;
import com.yhgc.api.mapper.VerificationMapper;
import com.yhgc.api.service.VerificationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 易生雄
 * @since 2023-03-28
 */
@Service
public class VerificationServiceImpl extends ServiceImpl<VerificationMapper, Verification> implements VerificationService {

    @Resource
    private VerificationMapper verificationMapper;

    @Override
    public Boolean saveVerification(Verification verification) {
        return verificationMapper.saveVerification(verification);
    }
}
