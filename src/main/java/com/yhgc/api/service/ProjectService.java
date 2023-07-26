package com.yhgc.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.entity.Project;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yhgc.api.vo.ProjectNameVo;
import com.yhgc.api.vo.ProjectVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 工程 服务类
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-23
 */
public interface ProjectService extends IService<Project> {


    /**
     *分页查询全部工程信息
     * @param page
     * @param query
     * @return
     */
    IPage<ProjectVo> pageQueryAllProject(Page<ProjectVo> page, @Param("query") String query,@Param("compId")String compId);


    /**
     *查询所有工程名称
     * @return
     */
    List<ProjectNameVo> verificationList();

}
