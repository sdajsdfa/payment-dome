package com.yhgc.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yhgc.api.entity.AppName;
import com.yhgc.api.entity.AppVersionList;
import com.yhgc.api.enums.AppStatusEnum;
import com.yhgc.api.service.AppNameService;
import com.yhgc.api.service.AppVersionListService;
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
public class AppNameController {

    @Resource
    private AppNameService appnameService;


    @Resource
    private AppVersionListService appversionlistService;

    /**
     *查询所有APP版本列表
     * @return
     */
    @ApiOperation("查询APP名称")
    @GetMapping(value = "/queryAllAppname")
    public R queryAllAppname() {
        QueryWrapper<AppName> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("AppStatus", AppStatusEnum.NORMAL.getCode());
        List<AppName> list = appnameService.list(queryWrapper);
        return R.ok(list);
    }


    /**
     *添加APP版本列表
     * @return
     */
    @ApiOperation("添加APP版本列表")
    @PostMapping(value = "/addAppname")
    public R addAppname(AppName appname) {
        Map<String, Object> map = new HashMap<String, Object>();
        QueryWrapper<AppName> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("AppName",appname.getAppName());
        AppName name= appnameService.getOne(queryWrapper);
        if(name!=null){
            if(name.getAppStatus()==1){
                name.setAppStatus(AppStatusEnum.NORMAL.getCode());
                appnameService.updateById(name);
            }else {
                return R.error("名称不能重复");
            }
        }else {
            appname.setAppStatus(AppStatusEnum.NORMAL.getCode());
            Boolean app = appnameService.save(appname);
            if (!app) {
                R.error("添加APP名称失败");
            }
        }

        QueryWrapper<AppName> Wrapper = new QueryWrapper<>();
        Wrapper.eq("AppName",appname.getAppName());
        Wrapper.eq("AppStatus",AppStatusEnum.NORMAL.getCode());
        AppName AppOne = appnameService.getOne(Wrapper);

        QueryWrapper<AppVersionList> WrapperOne = new QueryWrapper<>();
        WrapperOne.eq("AppId",AppOne.getAppId());
        WrapperOne.eq("AppStatus",AppStatusEnum.NORMAL.getCode());
        List<AppVersionList> appversionlists = appversionlistService.list(WrapperOne);

        QueryWrapper<AppName> query = new QueryWrapper<>();
        query.eq("AppStatus",AppStatusEnum.NORMAL.getCode());
        List<AppName> listOne = appnameService.list(query);
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
    public R updateAppname(AppName appname) {
        Map<String, Object> map = new HashMap<String, Object>();
        QueryWrapper<AppName> queryWrapperOne = new QueryWrapper<>();
        queryWrapperOne.eq("AppStatus",AppStatusEnum.NORMAL.getCode());
        queryWrapperOne.eq("AppName",appname.getAppName());
        AppName name= appnameService.getOne(queryWrapperOne);
        if(name!=null){
            return R.error("名称不能重复");
        }
        Boolean app = appnameService.updateById(appname);
        if(!app){
            R.error("修改APP名称失败");
        }
        AppName appnameAppId = appnameService.getById(appname.getAppId());
        QueryWrapper<AppName> query = new QueryWrapper<>();
        query.eq("AppStatus",AppStatusEnum.NORMAL.getCode());
        List<AppName> list = appnameService.list(query);
        AppVersionList appversionlist = new AppVersionList();
        appversionlist.setAppName(appname.getAppName());
        QueryWrapper<AppVersionList> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("AppId",appname.getAppId());
        queryWrapper.eq("AppStatus",AppStatusEnum.NORMAL.getCode());
        appversionlistService.update(appversionlist,queryWrapper);

        List<AppVersionList> appversionlists =appversionlistService.list(queryWrapper);
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
    public R deleteAppname(AppName appname) {
        Map<String, Object> map = new HashMap<String, Object>();
        appname.setAppStatus(1);
        Boolean app = appnameService.updateById(appname);
        if(!app){
            R.error("删除APP版本列表失败");
        }
        QueryWrapper<AppVersionList> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("AppStatus",AppStatusEnum.NORMAL.getCode());
        queryWrapper.eq("AppId",appname.getAppId());
        List<AppVersionList> list = appversionlistService.list(queryWrapper);
        for (AppVersionList appversionlist:list) {
            appversionlist.setAppStatus(AppStatusEnum.DELETE.getCode());
            appversionlistService.updateById(appversionlist);
        }
        QueryWrapper<AppName> query = new QueryWrapper<>();
        query.orderByDesc("AppId");
        query.eq("AppStatus",AppStatusEnum.NORMAL.getCode());
        query.last("limit 1");
        AppName appName = appnameService.getOne(query);

        List<AppVersionList> list2=null;
        if(appName!=null) {
            QueryWrapper<AppVersionList> Wrapper = new QueryWrapper<>();
            Wrapper.eq("AppId", appName.getAppId());
            Wrapper.eq("AppStatus", AppStatusEnum.NORMAL.getCode());
            list2 = appversionlistService.list(Wrapper.orderByDesc("id"));
        }else {
            QueryWrapper<AppVersionList> Wrapper = new QueryWrapper<>();
            Wrapper.eq("AppStatus", AppStatusEnum.NORMAL.getCode());
            list2 = appversionlistService.list(Wrapper.orderByDesc("id"));
        }

        QueryWrapper<AppName> queryOne = new QueryWrapper<>();
        queryOne.eq("AppStatus",AppStatusEnum.NORMAL.getCode());
        List<AppName> AppnameOne = appnameService.list(queryOne);

        map.put("appNameList",appName);
        map.put("AppnameOne",AppnameOne);
        map.put("list2",list2);
        return R.ok(map);
    }

}

