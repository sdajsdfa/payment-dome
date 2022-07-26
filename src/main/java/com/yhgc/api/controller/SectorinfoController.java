package com.yhgc.api.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.entity.Sectorinfo;
import com.yhgc.api.service.SectorinfoService;
import com.yhgc.api.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * 行业分类 前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-27
 */
@Api(tags = "行业分类")
@RestController
@RequestMapping("/sectorinfo")
public class SectorinfoController {

    @Resource
    private SectorinfoService sectorinfoService;


    /**
     *查询所有行业分类
     * @return
     */
    @ApiOperation("查询所有行业分类")
    @GetMapping(value = "/queryAllSectorInfo")
    public R queryAllSectorInfo(Integer pagesize, Integer pagenum) {
        Page<Sectorinfo> page = new Page<>(pagenum,pagesize);
        //根据条件查询数据
        IPage<Sectorinfo> iPage = sectorinfoService.page(page, null);
        return R.ok(iPage);
    }

    /**
     *  添加行业分类
     * @param sectorinfo
     * @return
     */
    @ApiOperation("添加行业分类")
    @PostMapping(value = "/addSectorInfo")
    public R addSectorInfo(@RequestBody Sectorinfo sectorinfo) {
        String sectorName = sectorinfo.getSectorName();
        QueryWrapper<Sectorinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sectorName",sectorName);
        Sectorinfo s =  sectorinfoService.getOne(queryWrapper);
        if (s != null){
            return R.error("行业名称已经被注册");
        }
        sectorinfo.setCreateTime(new Date());
        Boolean si = sectorinfoService.save(sectorinfo);
        if (!si) {
            return R.error("添加失败");
        }
        return R.ok(sectorinfo);
    }

    /**
     *  修改行业分类
     * @param sectorinfo
     * @return
     */
    @ApiOperation("修改行业分类")
    @PostMapping(value = "/updateSectorInfo")
    public R updateSectorInfo(@RequestBody Sectorinfo sectorinfo) {
        QueryWrapper<Sectorinfo> query = new QueryWrapper<>();
        query.eq("sectorName",sectorinfo.getSectorName());
        Sectorinfo s =  sectorinfoService.getOne(query);
        sectorinfo.setCreateTime(new Date());
        if(s != null){
            if(sectorinfo.getId().equals(s.getId())){
            }else {
                return R.error("行业名称已经被注册");
            }
        }
        Boolean si = sectorinfoService.updateById(sectorinfo);
        if (!si) {
            return R.error("修改失败");
        }
        return R.ok(sectorinfo);
    }
}

