package com.yhgc.api.service.impl;

import com.yhgc.api.entity.Analysisinfo;
import com.yhgc.api.mapper.AnalysisinfoMapper;
import com.yhgc.api.mapper.DatainfoMapper;
import com.yhgc.api.service.AnalysisinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 分析结果 服务实现类
 * </p>
 *
 * @author 易生雄
 * @since 2022-08-31
 */
@Service
public class AnalysisinfoServiceImpl extends ServiceImpl<AnalysisinfoMapper, Analysisinfo> implements AnalysisinfoService {

    @Resource
    private AnalysisinfoMapper analysisinfoMapper;

    @Override
    public List<Map<Date,Integer>> countAnalysis(Integer unitId) {
        return analysisinfoMapper.countAnalysis(unitId);
    }
}
