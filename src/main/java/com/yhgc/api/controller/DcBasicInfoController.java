package com.yhgc.api.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yhgc.api.entity.DcBasicInfo;
import com.yhgc.api.entity.DcChannel;
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
public class DcBasicInfoController extends BaseController {
    /**
     *  新增低应变试验数据
     * @param dcBasicInfo
     * @return
     */
    @ApiOperation("新增低应变试验数据")
    @PostMapping(value = "/DyData")
    public R dyData(@RequestBody DcBasicInfo dcBasicInfo) {
        dcBasicInfo.setCreateTime(new Date());
        dcBasicInfo.setIsHighStrainTest(0);
        dcBasicInfo.setCreateName("RS");
        dcBasicInfo.setComId((String) redisTemplate.opsForValue().get("CompId"));
        System.out.println(dcBasicInfo+"==================================1");
        Boolean dy = dcBasicinfoService.save(dcBasicInfo);
        if(dcBasicInfo.getChannels()!=null){
            for (int i=0;i<dcBasicInfo.getChannels().size();i++){
                dcBasicInfo.getChannels().get(i).setCreateTime(new Date());
                dcBasicInfo.getChannels().get(i).setCreateName("RS");
                dcBasicInfo.getChannels().get(i).setBasicInfoId(dcBasicInfo.getBasicInfoId());
                dcChannelService.save(dcBasicInfo.getChannels().get(i));
            }
        }
        if (!dy) {
            return R.error("新增低应变试验数据失败");
        }
        return R.ok("新增低应变试验数据成功!");
    }

    /**
     *  新增高应变试验数据
     * @param dcBasicInfo
     * @return
     */
    @ApiOperation("新增高应变试验数据")
    @PostMapping(value = "/GyData")
    public R gyData(@RequestBody DcBasicInfo dcBasicInfo) {
        dcBasicInfo.setCreateTime(new Date());
        dcBasicInfo.setIsHighStrainTest(1);
        dcBasicInfo.setCreateName("RS");
        dcBasicInfo.setComId((String) redisTemplate.opsForValue().get("CompId"));
        System.out.println(dcBasicInfo+"==================================2");
        Boolean gy = dcBasicinfoService.save(dcBasicInfo);
        if(dcBasicInfo.getChannels()!=null){
            for (int i=0;i<dcBasicInfo.getChannels().size();i++){
                dcBasicInfo.getChannels().get(i).setCreateTime(new Date());
                dcBasicInfo.getChannels().get(i).setCreateName("RS");
                dcBasicInfo.getChannels().get(i).setBasicInfoId(dcBasicInfo.getBasicInfoId());
                dcChannelService.save(dcBasicInfo.getChannels().get(i));
            }
        }
        if (!gy) {
            return R.error("新增高应变试验数据失败");
        }
        return R.ok("新增高应变试验数据成功!");
    }

    /**
     * 查询所有数据
     */
    @ApiOperation("查询所有低应变数据")
    @PostMapping(value = "/DyData/GetPileList")
    public R GetPileList(String SerialNo){
        QueryWrapper<DcBasicInfo> query = new QueryWrapper<>();
        query.eq("SerialNo",SerialNo);
        List<DcBasicInfo> dcBasicInfo = dcBasicinfoService.list(query);
        return R.ok(dcBasicInfo);
    }

    /**
     *   根据ID查询低应变数据
     */
    @ApiOperation("根据ID查询低应变数据")
    @PostMapping(value = "/DyData/GetById")
    public R GetById(Integer BasicInfoId){
        DcBasicInfo dcBasicInfo = dcBasicinfoService.getById(BasicInfoId);
        QueryWrapper<DcChannel> query = new QueryWrapper<>();
        query.eq("BasicInfoId",dcBasicInfo.getBasicInfoId());
        List<DcChannel> dcChannels = dcChannelService.list(query);
        dcBasicInfo.setChannels(dcChannels);
        return R.ok(dcBasicInfo);
    }

    /**
     * 查询所有高应变数据
     */
    @ApiOperation("查询所有高应变数据")
    @PostMapping(value = "/GYData/GetEntrustInfoBySerialNo")
    public R GetEntrustInfoBySerialNo(String SerialNo){
        QueryWrapper<DcBasicInfo> query = new QueryWrapper<>();
        query.eq("SerialNo",SerialNo);
        List<DcBasicInfo> dcBasicInfo = dcBasicinfoService.list(query);
        return R.ok(dcBasicInfo);
    }

}

