package com.yhgc.api.service.impl;

import com.yhgc.api.entity.CompanyInfo;
import com.yhgc.api.mapper.CompanyInfoMapper;
import com.yhgc.api.mapper.CompanyStructMapper;
import com.yhgc.api.service.CompanyInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yhgc.api.vo.CompanyInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 单位基本信息 服务实现类
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-25
 */
@Service
public class CompanyInfoServiceImpl extends ServiceImpl<CompanyInfoMapper, CompanyInfo> implements CompanyInfoService {

    @Resource
    private CompanyInfoMapper companyInfoMapper;

    @Override
    public CompanyInfoVo companyStructList(String CompId) {
        return companyInfoMapper.companyStructList(CompId);
    }
}
