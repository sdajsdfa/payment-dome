package com.yhgc.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yhgc.api.entity.Appname;
import com.yhgc.api.entity.Appversionlist;
import com.yhgc.api.service.AppnameService;
import com.yhgc.api.service.AppversionlistService;
import com.yhgc.api.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2022-12-09
 */
@Api(tags = "APP名称")
@RestController
@RequestMapping("/appname")
public class AppnameController {

    @Resource
    private AppnameService appnameService;


    @Resource
    private AppversionlistService appversionlistService;

    /**
     *查询所有APP版本列表
     * @return
     */
    @ApiOperation("查询APP名称")
    @GetMapping(value = "/queryAllAppname")
    public R queryAllAppname() {
        QueryWrapper<Appname> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("AppStatus",0);
        List<Appname> list = appnameService.list(queryWrapper);
        return R.ok(list);
    }


    /**
     *添加APP版本列表
     * @return
     */
    @ApiOperation("添加APP版本列表")
    @PostMapping(value = "/addAppname")
    public R addAppname(Appname appname) {
        Map<String, Object> map = new HashMap<String, Object>();
        QueryWrapper<Appname> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("AppName",appname.getAppName());
        Appname name= appnameService.getOne(queryWrapper);
        if(name!=null){
            if(name.getAppStatus()==1){
                name.setAppStatus(0);
                appnameService.updateById(name);
            }else {
                return R.error("名称不能重复");
            }
        }else {
            appname.setAppStatus(0);
            Boolean app = appnameService.save(appname);
            if (!app) {
                R.error("添加APP名称失败");
            }
        }

        QueryWrapper<Appname> Wrapper = new QueryWrapper<>();
        Wrapper.eq("AppName",appname.getAppName());
        Wrapper.eq("AppStatus",0);
        Appname AppOne = appnameService.getOne(Wrapper);

        QueryWrapper<Appversionlist> WrapperOne = new QueryWrapper<>();
        WrapperOne.eq("AppId",AppOne.getAppId());
        WrapperOne.eq("AppStatus",0);
        List<Appversionlist> appversionlists = appversionlistService.list(WrapperOne);

        QueryWrapper<Appname> query = new QueryWrapper<>();
        query.eq("AppStatus",0);
        List<Appname> listOne = appnameService.list(query);
        map.put("listOne",listOne);
        map.put("AppOne",AppOne);
        map.put("appversionlists",appversionlists);
        return R.ok(map);
    }

    /**
     *修改APP版本列表
     * @return
     */
    @ApiOperation("修改APP版本列表")
    @PostMapping(value = "/updateAppname")
    public R updateAppname(Appname appname) {
        Map<String, Object> map = new HashMap<String, Object>();
        QueryWrapper<Appname> queryWrapperOne = new QueryWrapper<>();
        queryWrapperOne.eq("AppStatus",0);
        queryWrapperOne.eq("AppName",appname.getAppName());
        Appname name= appnameService.getOne(queryWrapperOne);
        if(name!=null){
            return R.error("名称不能重复");
        }
        Boolean app = appnameService.updateById(appname);
        if(!app){
            R.error("修改APP名称失败");
        }
        Appname appnameAppId = appnameService.getById(appname.getAppId());
        QueryWrapper<Appname> query = new QueryWrapper<>();
        query.eq("AppStatus",0);
        List<Appname> list = appnameService.list(query);
        Appversionlist appversionlist = new Appversionlist();
        appversionlist.setAppName(appname.getAppName());
        QueryWrapper<Appversionlist> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("AppId",appname.getAppId());
        queryWrapper.eq("AppStatus",0);
        appversionlistService.update(appversionlist,queryWrapper);

        List<Appversionlist> appversionlists =appversionlistService.list(queryWrapper);
        map.put("appversionlists",appversionlists);
        map.put("listapp",list);
        map.put("appnameAppId",appnameAppId);
        return R.ok(map);
    }

    /**
     *删除APP名称
     * @return
     */
    @ApiOperation("删除APP名称")
    @PostMapping (value = "/deleteAppname")
    public R deleteAppname(Appname appname) {
        Map<String, Object> map = new HashMap<String, Object>();
        appname.setAppStatus(1);
        Boolean app = appnameService.updateById(appname);
        if(!app){
            R.error("删除APP版本列表失败");
        }
        QueryWrapper<Appversionlist> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("AppStatus",0);
        queryWrapper.eq("AppId",appname.getAppId());
        List<Appversionlist> list = appversionlistService.list(queryWrapper);
        for (Appversionlist appversionlist:list) {
            appversionlist.setAppStatus(1);
            appversionlistService.updateById(appversionlist);
        }
        QueryWrapper<Appname> query = new QueryWrapper<>();
        query.orderByDesc("AppId");
        query.eq("AppStatus",0);
        query.last("limit 1");
        Appname  appName = appnameService.getOne(query);

        List<Appversionlist> list2=null;
        if(appName!=null) {
            QueryWrapper<Appversionlist> Wrapper = new QueryWrapper<>();
            Wrapper.eq("AppId", appName.getAppId());
            Wrapper.eq("AppStatus", 0);
            list2 = appversionlistService.list(Wrapper.orderByDesc("id"));
        }else {
            QueryWrapper<Appversionlist> Wrapper = new QueryWrapper<>();
            Wrapper.eq("AppStatus", 0);
            list2 = appversionlistService.list(Wrapper.orderByDesc("id"));
        }

        QueryWrapper<Appname> queryOne = new QueryWrapper<>();
        queryOne.eq("AppStatus",0);
        List<Appname> AppnameOne = appnameService.list(queryOne);

        map.put("appNameList",appName);
        map.put("AppnameOne",AppnameOne);
        map.put("list2",list2);
        return R.ok(map);
    }

}

