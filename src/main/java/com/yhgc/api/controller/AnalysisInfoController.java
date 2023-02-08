package com.yhgc.api.controller;

import com.yhgc.api.entity.AnalysisInfo;
import com.yhgc.api.service.AnalysisInfoService;
import com.yhgc.api.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 分析结果 前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2022-08-31
 */
@Api(tags = "分析结果")
@RestController
@RequestMapping("/analysisinfo")
public class AnalysisInfoController {

    @Resource
    private AnalysisInfoService analysisinfoService;


    /**
     * 添加数据分析结果
     * @param analysisinfo
     * @return
     */
    @ApiOperation("添加数据分析结果")
    @PostMapping(value = "/addDpt")
    public R addDpt(@RequestBody AnalysisInfo analysisinfo) {
        analysisinfo.setAnalyFileTime(new Date());
        analysisinfo.setDeclareTime(new Date());
        analysisinfo.setDataStatus(0);
        Boolean ai = analysisinfoService.save(analysisinfo);
        if (!ai) {
            return R.error("添加失败");
        }
        return R.ok(analysisinfo);
    }


    /**
     * 查询统计近三个月数据分析结果
     * @param unitId
     * @return
     */
    @ApiOperation("查询统计数据分析结果")
    @PostMapping(value = "/countAnalysisResults")
    public R countAnalysisResults(Integer unitId){
        List<Map<Date,Integer>> countAnalysis= analysisinfoService.countAnalysis(unitId);
        return R.ok(countAnalysis);
    }

}

