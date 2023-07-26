package com.yhgc.api.mapper;

import com.yhgc.api.entity.CompanyStruct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yhgc.api.vo.CompanyInfoVo;
import com.yhgc.api.vo.CompanyStructVo;

import java.util.List;

/**
 * <p>
 * 组织架构 Mapper 接口
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-25
 */
public interface CompanyStructMapper extends BaseMapper<CompanyStruct> {

    /**
     * 查询所有部门名臣
     */
    List<CompanyStructVo> queryDepartName();

}
