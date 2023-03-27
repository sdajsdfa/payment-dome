package com.yhgc.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.entity.MethodInfo;
import com.yhgc.api.enums.StatusEnum;
import com.yhgc.api.service.MethodInfoService;
import com.yhgc.api.service.ProjectFilesService;
import com.yhgc.api.service.ProjectInfoService;
import com.yhgc.api.service.UserinfoLoginToken;
import com.yhgc.api.util.R;
import com.yhgc.api.vo.MethodInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.*;


/**
 * <p>
 * 检测方法分类 前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-27
 */
@Api(tags = "检测方法分类")
@RestController
@RequestMapping("/methodInfo")
@Transactional
public class MethodInfoController {

    @Resource
    private MethodInfoService methodInfoService;

    @Resource
    private ProjectFilesService projectfilesService;

    @Resource
    private ProjectInfoService projectinfoService;

    /**
     * 查询检测方法分类
     *
     * @param id
     * @return
     */
    @ApiOperation("查询检测方法分类")
    @GetMapping(value = "/queryByMethodInfo")
    @UserinfoLoginToken
    public R queryByMethodInfo(Long id) {
        Map<String, Object> map = new HashMap<>();
        MethodInfo methodInfo = methodInfoService.getByIdMethodInfo(id);
        map.put("methodInfoDetailed",methodInfo);
        return R.ok(map);
    }


    /**
     * 查询检测方法分类
     *
     * @return
     */
    @ApiOperation("查询检测方法分类")
    @GetMapping(value = "/queryAllMethodInfo")
//    @UserinfoLoginToken
    public R queryAllMethodInfo(Integer pageNum, Integer pageSize, String query) {
        Map<String, Object> map = new HashMap<>();
        MethodInfo methodInfo = new MethodInfo();
        if(pageNum==null||pageSize==null){
            pageNum=1;
            pageSize=10;
        }
        Page<MethodInfo> page = new Page<>(pageNum, pageSize);
        if (!(query.equals(""))) {
            if (ProjectInfoController.isNumeric(query)) {
                methodInfo.setId(Long.valueOf(query));
            } else {
                methodInfo.setTestMthod(query);
            }
        } else {
            methodInfo.setTestMthod("");
        }
        System.out.println(methodInfo.getId());
        QueryWrapper<MethodInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 2);
        queryWrapper.like("testMthod", methodInfo.getTestMthod()).or().eq("id", methodInfo.getId());
        IPage<MethodInfo> methodInfoIPage = methodInfoService.page(page, queryWrapper);
        map.put("methodInfo", methodInfoIPage);
        return R.ok(map);
    }

    /**
     * 查询检测方法分类
     *
     * @return
     */
    @ApiOperation("查询检测方法分类")
    @GetMapping(value = "/queryAllByMethodInfo")
//    @UserinfoLoginToken
    public R queryAllByMethodInfo(Integer pageNum, Integer pageSize, String query) {
        Map<String, Object> map = new HashMap<>();
        if(pageNum==null||pageSize==null){
            pageNum=1;
            pageSize=10;
        }
        Page<MethodInfoVo> page = new Page<>(pageNum, pageSize);
        IPage<MethodInfoVo> iPage = methodInfoService.searchPage(page,query);
        map.put("methodInfo", iPage);
        return R.ok(map);
    }



    /**
     * 添加和修改检测方法分类
     *
     * @param methodInfo
     * @return
     */
    @ApiOperation(value = "添加和修改检测方法分类",httpMethod = "POST")
    @PostMapping (value = "/addUpdateMethodInfo")
    @UserinfoLoginToken
    public R addUpdateMethodInfo(@RequestBody MethodInfo methodInfo) {
        Map<String,Object> map = new HashMap<>();
        if(methodInfo.getId()<0){
            if(methodInfo.getMethodId()==null){
                methodInfo.setMethodId(-1);
            }
            if(methodInfo.getMethodId()!=-1){
                if(projectinfoService.getById(methodInfo.getMethodId())==null){
                    return R.error("必须要有工程名称");
                };
            }
            methodInfo.setCreateTime(new Date());
            methodInfo.setStatus(0);
            int ui = methodInfoService.saveMethodInfo(methodInfo);
            if (ui==1) {
                map.put("methodInfo",methodInfoService.getById(methodInfo.getId()));
                return R.ok(map);
            }
            return R.error("添加检测项目失败");
        }else {
            QueryWrapper<MethodInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", methodInfo.getId());
            methodInfo.setCreateTime(new Date());
            Boolean p = methodInfoService.update(methodInfo,queryWrapper);
            if (!p) {
                return R.error("修改检测方法分类失败");
            }
            return R.ok("修改检测方法分类成功");
        }
    }




    /**
     *  添加检测项目
     * @param methodInfo
     * @return
     */
    @ApiOperation("添加检测项目")
    @PostMapping("/saveMethodInfo")
    @UserinfoLoginToken
    public R saveMethodInfo(@RequestBody MethodInfo methodInfo) {
        Map<String,Object> map = new HashMap<>();
        methodInfo.setCreateTime(new Date());
        methodInfo.setStatus(0);
        methodInfo.setMethodId(-1);
        int ui = methodInfoService.saveMethodInfo(methodInfo);
        if (ui==1) {
            QueryWrapper<MethodInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", methodInfo.getId());
            map.put("methodInfo",methodInfoService.list(queryWrapper));
            return R.ok(map);
        }
        return R.error("添加检测项目失败");
    }

    /**
     *  修改检测项目
     * @param methodInfo
     * @return
     */
    @ApiOperation("修改检测项目")
    @PostMapping("/updateMethodInfo")
    @UserinfoLoginToken
    public R updateMethodInfo(@RequestBody MethodInfo methodInfo) {
        QueryWrapper<MethodInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", methodInfo.getId());
        Boolean u = methodInfoService.update(methodInfo,queryWrapper);
        if (!u) {
            return R.error("修改检测项目失败");
        }
        return R.ok();
    }

    /**
     *  删除检测项目
     * @param id
     * @return
     */
    @ApiOperation("删除检测项目")
    @GetMapping("/deleteMethodInfo")
    @UserinfoLoginToken
    public R deleteMethodInfo(Long id) {
        QueryWrapper<MethodInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        MethodInfo methodInfo = new MethodInfo();
        methodInfo.setStatus(StatusEnum.DELETE.getCode());
        //将实体对象进行包装，包装为操作条件
        Boolean pi = methodInfoService.update(methodInfo, queryWrapper);
        if (!pi) {
            return R.error("删除检测项目失败");
        }
        return R.ok("删除检测项目成功");
    }



    /**
     * 查询检测方法分类
     * @param methodId
     * @return
     */
    @ApiOperation("查询工程详细信息")
    @GetMapping(value = "/queryMethodInfo")
    @UserinfoLoginToken
    public R queryMethodInfo(Long methodId) {
        Map<String,Object> map = new HashMap<>();
        QueryWrapper<MethodInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("methodId",methodId);
        queryWrapper.eq("status",0);
        List<MethodInfo> methodInfo = methodInfoService.list(queryWrapper);
        map.put("methodInfo",methodInfo);
        return R.ok(map);
    }

    /**
     * 删除全部
     * @param
     * @return
     */
    @ApiOperation("删除全部")
    @GetMapping(value = "/deleteAllMethodInfo")
    @UserinfoLoginToken
    public R deleteAllMethodInfo() {
        QueryWrapper<MethodInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("methodId",-1);
        List<MethodInfo> methodInfo = methodInfoService.list(queryWrapper);
        for (MethodInfo info: methodInfo) {
//            info.setStatus(0);
            QueryWrapper<MethodInfo> queryWrapperOne = new QueryWrapper<>();
            queryWrapper.eq("methodId",info.getMethodId());
            methodInfoService.remove(queryWrapperOne);
        }
        return R.ok("删除检测项目成功");
    }

    /**
     * 查询检测方法分类
     *
     * @param
     * @return
     */
    @ApiOperation("查询检测方法分类")
    @GetMapping(value = "/queryByMethodInfoId")
    @UserinfoLoginToken
    public R queryByMethodInfoId() {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<MethodInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("methodId",-1);
        queryWrapper.eq("status",0);
        List<MethodInfo> methodInfo = methodInfoService.list(queryWrapper);
        map.put("methodInfo",methodInfo);
        return R.ok(map);
    }
}

