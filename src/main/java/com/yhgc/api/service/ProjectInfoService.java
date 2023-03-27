package com.yhgc.api.service;

import com.yhgc.api.entity.ProjectInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yhgc.api.vo.ProjectInfoVo;

import java.util.List;

/**
 * <p>
 * 工程信息 服务类
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-28
 */
public interface ProjectInfoService extends IService<ProjectInfo> {

   Boolean saveProject (ProjectInfo projectInfo);

   Boolean updateProject(ProjectInfo projectInfo);

   List<ProjectInfoVo> verificationList();

}
