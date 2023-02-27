package com.yhgc.api.controller;

import com.yhgc.api.entity.NewsInfo;
import com.yhgc.api.service.NewsInfoService;
import com.yhgc.api.util.R;
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
@RequestMapping("/newsInfo")
public class NewsInfoController {


    @Resource
    private NewsInfoService newsinfoService;


    /**
     *  添加产品和新闻
     * @param newsinfo
     * @return
     */
    @ApiOperation("添加产品和新闻")
    @PostMapping(value = "/addNews")
    public R addNews(@RequestBody NewsInfo newsinfo) {
        newsinfo.setCreateTime(new Date());
        newsinfo.setDate(new Date());
        newsinfo.setStatus(0);
        Boolean si = newsinfoService.save(newsinfo);
        if (!si) {
            return R.error("添加失败");
        }
        return R.ok(newsinfo);
    }

    /**
     *查询产品和新闻最近各15条数据
     * @return
     */
    @ApiOperation("查询产品和新闻最近各15条数据")
    @PostMapping(value = "/queryNews")
    public R queryNews() {
        List<NewsInfo> newsinfo = newsinfoService.queryNews();
        List<NewsInfo> productinfo = newsinfoService.queryProduct();
        for (NewsInfo info:
        productinfo) {
            newsinfo.add(info);
        }
        return R.ok(newsinfo);
    }
}

