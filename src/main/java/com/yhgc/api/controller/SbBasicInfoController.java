package com.yhgc.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yhgc.api.entity.SbBasicInfo;
import com.yhgc.api.entity.SbSection;
import com.yhgc.api.service.UserinfoLoginToken;
import com.yhgc.api.util.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2023-06-25
 */
@RestController
@RequestMapping("/api")
public class  SbBasicInfoController extends BaseController {

    /**
     *  新增声测试验基础数据
     * @param sbBasicInfo
     * @return
     */
    @ApiOperation("新增声测试验基础数据")
    @PostMapping(value = "/ScData")
//    @UserinfoLoginToken
    public R scData(@RequestBody SbBasicInfo sbBasicInfo) {
        sbBasicInfo.setCreateTime(new Date());
        sbBasicInfo.setCreateName("RS");
        sbBasicInfo.setComId((String) redisTemplate.opsForValue().get("CompId"));
        Boolean sc = sbBasicInfoService.save(sbBasicInfo);
        if (!sc) {
            return R.error("新增声测试验基础数据失败");
        }
        return R.ok("新增声测试验基础数据成功");
    }

    /**
     * 查询所有数据
     */
    @ApiOperation("查询所有声测数据")
    @PostMapping(value = "/ScData/GetPileList")
    public R GetPileList(String SerialNo){
        QueryWrapper<SbBasicInfo> query = new QueryWrapper<>();
        query.eq("SerialNo",SerialNo);
        List<SbBasicInfo> sbBasicInfo= sbBasicInfoService.list(query);
        return R.ok(sbBasicInfo);
    }

    /**
     * 查询ID查询声测数据
     */
    @ApiOperation("查询ID查询声测数据")
    @PostMapping(value = "/ScData/GetById")
    public R GetById(Integer BasicInfoId){
        SbBasicInfo sbBasicInfo = sbBasicInfoService.getById(BasicInfoId);
        QueryWrapper<SbSection> query = new QueryWrapper<>();
        query.eq("BasicInfoId",sbBasicInfo.getBasicInfoId());
        List<SbSection> dcChannels = sbSectionService.list(query);
        return R.ok(sbBasicInfo);
    }

}

