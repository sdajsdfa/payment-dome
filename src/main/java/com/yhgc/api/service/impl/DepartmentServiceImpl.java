package com.yhgc.api.service.impl;

import com.yhgc.api.entity.Department;
import com.yhgc.api.mapper.DepartmentMapper;
import com.yhgc.api.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yhgc.api.vo.DepartmentVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 部门 服务实现类
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-28
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public List<DepartmentVo> verificationList() {
        return departmentMapper.verificationList();
    }
}
