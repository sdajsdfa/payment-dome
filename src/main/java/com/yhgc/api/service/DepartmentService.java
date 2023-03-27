package com.yhgc.api.service;

import com.yhgc.api.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yhgc.api.vo.DepartmentVo;
import com.yhgc.api.vo.ProjectInfoVo;

import java.util.List;

/**
 * <p>
 * 部门 服务类
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-28
 */
public interface DepartmentService extends IService<Department> {

    List<DepartmentVo> verificationList();


}
