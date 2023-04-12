package com.yhgc.api.mapper;

import com.yhgc.api.entity.Verification;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 易生雄
 * @since 2023-03-28
 */
public interface VerificationMapper extends BaseMapper<Verification> {

    Boolean saveVerification(@RequestBody Verification verification);

}
