package com.yhgc.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.entity.MethodInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yhgc.api.vo.MethodInfoVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 检测方法分类 服务类
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-28
 */
public interface MethodInfoService extends IService<MethodInfo> {

    int saveMethodInfo(MethodInfo methodInfo);

    IPage<MethodInfoVo> searchPage(Page<MethodInfoVo> page, @Param("query") String query);

    MethodInfoVo getByIdMethodInfo(Long id);
}
