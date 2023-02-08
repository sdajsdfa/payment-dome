package com.yhgc.api.service.impl;

import com.yhgc.api.entity.ProjectFiles;
import com.yhgc.api.mapper.ProjectFilesMapper;
import com.yhgc.api.service.ProjectFilesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author 易生雄
 * @since 2022-07-06
 */
@Service
public class ProjectFilesServiceImpl extends ServiceImpl<ProjectFilesMapper, ProjectFiles> implements ProjectFilesService {
    @Resource
    private ProjectFilesMapper projectfilesMapper;

    @Override
    public List<ProjectFiles> queryProjectFileList(Integer unitId) {
        return projectfilesMapper.queryProjectFileList(unitId);
    }
}
