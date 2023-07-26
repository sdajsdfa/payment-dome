package com.yhgc.api.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.entity.Project;
import com.yhgc.api.mapper.ProjectMapper;
import com.yhgc.api.service.ProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yhgc.api.vo.ProjectNameVo;
import com.yhgc.api.vo.ProjectVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 工程 服务实现类
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-23
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

    @Resource
    private ProjectMapper projectMapper;

    @Override
    public IPage<ProjectVo> pageQueryAllProject(Page<ProjectVo> page, String query,String compId) {
        return projectMapper.pageQueryAllProject(page,query,compId);
    }

    @Override
    public List<ProjectNameVo> verificationList() {
        return projectMapper.verificationList();
    }
}
