package com.yhgc.api.controller;

import com.yhgc.api.entity.Newsinfo;
import com.yhgc.api.service.NewsinfoService;
import com.yhgc.api.vo.RestResult;
import com.yhgc.api.vo.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 产品和新闻 前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2022-09-05
 */
@Api(tags = "产品和新闻")
@RestController
@RequestMapping("/newsinfo")
public class NewsinfoController {


    @Resource
    private NewsinfoService newsinfoService;

    @Resource
    private ResultGenerator generator;

    /**
     *  添加产品和新闻
     * @param newsinfo
     * @return
     */
    @ApiOperation("添加产品和新闻")
    @PostMapping(value = "/addNews")
    public RestResult addNews(@RequestBody Newsinfo newsinfo) {
        if (newsinfo == null) {
            generator.getFailResult("产品和新闻不能为空");
        }
        newsinfo.setCreateTime(new Date());
        newsinfo.setDate(new Date());
        newsinfo.setStatus(0);
        Boolean si = newsinfoService.save(newsinfo);
        if (si != true) {
            return generator.getFailResult("添加失败");
        }
        return generator.getSuccessResult(newsinfo);
    }

    /**
     *查询产品和新闻最近各15条数据
     * @return
     */
    @ApiOperation("查询产品和新闻最近各15条数据")
    @PostMapping(value = "/queryNews")
    public RestResult queryNews() {
        List<Newsinfo> newsinfo = newsinfoService.queryNews();
        List<Newsinfo> productinfo = newsinfoService.queryProduct();
        for (Newsinfo info:
        productinfo) {
            newsinfo.add(info);
        }
        return generator.getSuccessResult(newsinfo);
    }
}

