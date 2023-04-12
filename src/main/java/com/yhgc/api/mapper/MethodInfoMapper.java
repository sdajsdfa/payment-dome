package com.yhgc.api.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.entity.MethodInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yhgc.api.vo.MethodInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 检测方法分类 Mapper 接口
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-28
 */
public interface MethodInfoMapper extends BaseMapper<MethodInfo> {

    int saveMethodInfo(MethodInfo methodInfo);

    IPage<MethodInfoVo> searchPage(@Param("page") Page<MethodInfoVo> page, @Param("query") String query);

    MethodInfoVo getByIdMethodInfo(@Param("id") Long id);

    List<MethodInfo> selectMenuInfo();

}
