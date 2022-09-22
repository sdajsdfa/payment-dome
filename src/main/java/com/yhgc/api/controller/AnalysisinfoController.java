package com.yhgc.api.controller;


import com.yhgc.api.entity.Analysisinfo;
import com.yhgc.api.service.AnalysisinfoService;
import com.yhgc.api.vo.RestResult;
import com.yhgc.api.vo.ResultGenerator;
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
public class AnalysisinfoController {

    @Resource
    private AnalysisinfoService analysisinfoService;

    @Resource
    private ResultGenerator generator;


    /**
     * 添加数据分析结果
     * @param analysisinfo
     * @return
     */
    @ApiOperation("添加数据分析结果")
    @PostMapping(value = "/addDpt")
    public RestResult addDpt(@RequestBody Analysisinfo analysisinfo) {
        if (analysisinfo == null) {
            generator.getFailResult("数据分析结果不能为空");
        }
        analysisinfo.setAnalyFileTime(new Date());
        analysisinfo.setDeclareTime(new Date());
        analysisinfo.setDataStatus(0);
        Boolean ai = analysisinfoService.save(analysisinfo);
        if (!ai) {
            return generator.getFailResult("添加失败");
        }
        return generator.getSuccessResult(analysisinfo);
    }


    /**
     * 查询统计近三个月数据分析结果
     * @param unitId
     * @return
     */
    @ApiOperation("查询统计数据分析结果")
    @PostMapping(value = "/countAnalysisResults")
    public RestResult countAnalysisResults(Integer unitId){
        List<Map<Date,Integer>> countAnalysis= analysisinfoService.countAnalysis(unitId);
        return generator.getSuccessResult(countAnalysis);
    }

}

