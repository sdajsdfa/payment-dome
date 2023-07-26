package com.yhgc.api.mapper;

import com.yhgc.api.entity.CompanyInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yhgc.api.vo.CompanyInfoVo;

import java.util.List;

/**
 * <p>
 * 单位基本信息 Mapper 接口
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-25
 */
public interface CompanyInfoMapper extends BaseMapper<CompanyInfo> {

    /**
     * 查询所有组织机构名称
     * @return
     */
    CompanyInfoVo companyStructList(String CompId);

}
