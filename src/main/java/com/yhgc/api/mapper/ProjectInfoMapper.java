package com.yhgc.api.mapper;

import com.yhgc.api.entity.ProjectInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yhgc.api.vo.ProjectInfoVo;

import java.util.List;

/**
 * <p>
 * 工程信息 Mapper 接口
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-28
 */
public interface ProjectInfoMapper extends BaseMapper<ProjectInfo> {

    Boolean saveProject(ProjectInfo projectInfo);

    Boolean updateProject(ProjectInfo projectInfo);

    List<ProjectInfoVo> verificationList();
}
