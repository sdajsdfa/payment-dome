package com.yhgc.api.service.impl;

import com.yhgc.api.entity.CompanyStruct;
import com.yhgc.api.mapper.CompanyStructMapper;
import com.yhgc.api.service.CompanyStructService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yhgc.api.vo.CompanyStructVo;
import com.yhgc.api.vo.RoleInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 组织架构 服务实现类
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-25
 */
@Service
public class CompanyStructServiceImpl extends ServiceImpl<CompanyStructMapper, CompanyStruct> implements CompanyStructService {

    @Resource
    private CompanyStructMapper companyStructMapper;

    @Override
    public List<CompanyStructVo> queryDepartName() {
        return companyStructMapper.queryDepartName();
    }
}
