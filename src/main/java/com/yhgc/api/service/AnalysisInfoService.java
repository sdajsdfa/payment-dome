package com.yhgc.api.service;

import com.yhgc.api.entity.AnalysisInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 分析结果 服务类
 * </p>
 *
 * @author 易生雄
 * @since 2022-08-31
 */
public interface AnalysisInfoService extends IService<AnalysisInfo> {


    /**
     * 查询统计近三个月数据分析结果
     * @param unitId
     * @return
     */
    List<Map<Date,Integer>> countAnalysis(Integer unitId);

}
