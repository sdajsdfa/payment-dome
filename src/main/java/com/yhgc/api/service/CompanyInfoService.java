package com.yhgc.api.service;

import com.yhgc.api.entity.CompanyInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yhgc.api.vo.CompanyInfoVo;

import java.util.List;

/**
 * <p>
 * 单位基本信息 服务类
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-25
 */
public interface CompanyInfoService extends IService<CompanyInfo> {


    /**
     * 查询所有组织机构名称
     * @return
     */
    CompanyInfoVo companyStructList(String CompId);

}
