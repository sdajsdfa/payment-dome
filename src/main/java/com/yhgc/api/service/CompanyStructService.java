package com.yhgc.api.service;

import com.yhgc.api.entity.CompanyStruct;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yhgc.api.vo.CompanyStructVo;
import com.yhgc.api.vo.RoleInfoVo;

import java.util.List;

/**
 * <p>
 * 组织架构 服务类
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-25
 */
public interface CompanyStructService extends IService<CompanyStruct> {


    /**
     * 查询所有部门名臣
     */
    List<CompanyStructVo> queryDepartName();

}
