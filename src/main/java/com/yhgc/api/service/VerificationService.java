package com.yhgc.api.service;

import com.yhgc.api.entity.Verification;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 易生雄
 * @since 2023-03-28
 */
public interface VerificationService extends IService<Verification> {

    Boolean saveVerification(Verification verification);


}
