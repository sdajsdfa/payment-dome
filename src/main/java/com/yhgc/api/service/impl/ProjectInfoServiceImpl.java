package com.yhgc.api.service.impl;

import com.yhgc.api.entity.ProjectInfo;
import com.yhgc.api.mapper.ProjectInfoMapper;
import com.yhgc.api.service.ProjectInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yhgc.api.vo.ProjectInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 工程信息 服务实现类
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-28
 */
@Service
public class ProjectInfoServiceImpl extends ServiceImpl<ProjectInfoMapper, ProjectInfo> implements ProjectInfoService {

    @Resource
    private ProjectInfoMapper projectInfoMapper;

    @Override
    public Boolean saveProject(ProjectInfo projectInfo) {
        return projectInfoMapper.saveProject(projectInfo);
    }

    @Override
    public Boolean updateProject(ProjectInfo projectInfo) {
        return projectInfoMapper.updateProject(projectInfo);
    }

    @Override
    public List<ProjectInfoVo> verificationList() {
        return projectInfoMapper.verificationList();
    }
}
