package com.yhgc.api.mapper;

import com.yhgc.api.entity.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yhgc.api.vo.DepartmentVo;
import com.yhgc.api.vo.ProjectInfoVo;

import java.util.List;

/**
 * <p>
 * 部门 Mapper 接口
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-28
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    List<DepartmentVo> verificationList();

}
