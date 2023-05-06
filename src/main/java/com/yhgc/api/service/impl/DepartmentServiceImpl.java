package com.yhgc.api.service.impl;

import com.yhgc.api.dto.DepartmentDto;
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

    @Override
    public List<DepartmentDto> dptList() {
        return departmentMapper.dptList();
    }

    @Override
    public DepartmentDto getDptById(Long id) {
        return departmentMapper.getDptById(id);
    }

    @Override
    public Boolean updateDepartment(DepartmentDto departmentDto) {
        return departmentMapper.updateDepartment(departmentDto);
    }

    @Override
    public Boolean saveDpt(DepartmentDto departmentDto) {
        return departmentMapper.saveDpt(departmentDto);
    }

    @Override
    public Department getOneDpt(String dptName) {
        return departmentMapper.getOneDpt(dptName);
    }
}
