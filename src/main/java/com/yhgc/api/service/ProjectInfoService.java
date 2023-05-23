package com.yhgc.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.dto.ProjectInfoDto;
import com.yhgc.api.entity.ProjectInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yhgc.api.vo.MethodInfoVo;
import com.yhgc.api.vo.ProjectInfoVo;
import org.apache.ibatis.annotations.Param;


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

   IPage<ProjectInfoDto> searchPage(Page<ProjectInfoDto> page, @Param("query") String query);

}
