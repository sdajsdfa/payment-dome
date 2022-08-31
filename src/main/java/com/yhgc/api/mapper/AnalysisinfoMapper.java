package com.yhgc.api.mapper;

import com.yhgc.api.entity.Analysisinfo;
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
public interface AnalysisinfoMapper extends BaseMapper<Analysisinfo> {

    List<Map<Date,Integer>> countAnalysis(Integer unitId);
}
