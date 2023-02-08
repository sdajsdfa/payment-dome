package com.yhgc.api.mapper;

import com.yhgc.api.entity.AnalysisInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 分析结果 Mapper 接口
 * </p>
 *
 * @author 易生雄
 * @since 2022-08-31
 */
public interface AnalysisInfoMapper extends BaseMapper<AnalysisInfo> {

    List<Map<Date,Integer>> countAnalysis(Integer unitId);
}
