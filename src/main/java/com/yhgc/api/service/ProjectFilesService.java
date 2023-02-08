package com.yhgc.api.service;

import com.yhgc.api.entity.ProjectFiles;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * VIEW 服务类
 * </p>
 *
 * @author 易生雄
 * @since 2022-07-06
 */
public interface ProjectFilesService extends IService<ProjectFiles> {
    List<ProjectFiles> queryProjectFileList(Integer unitId);
}
