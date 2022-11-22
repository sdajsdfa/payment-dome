package com.yhgc.api.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yhgc.api.entity.Projectinfo;
import com.yhgc.api.entity.Sectorinfo;
import com.yhgc.api.entity.Serverlist;
import com.yhgc.api.enums.StatusEnum;
import com.yhgc.api.service.ServerlistService;
import com.yhgc.api.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


/**
 * <p>
 * 服务器列表 前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2022-11-07
 */
@Api(tags = "服务器列表")
@RestController
@RequestMapping("/serverlist")
public class ServerlistController {

    @Resource
    private ServerlistService serverlistService;

    /**
     *查询所有服务器列表
     * @return
     */
    @ApiOperation("查询所有服务器列表")
    @GetMapping(value = "/queryAllServerlist")
    public List queryAllServerlist() {
        List<Serverlist>  list = serverlistService.list();
        return list;
    }

    /**
     *查询所有服务器列表
     * @return
     */
    @ApiOperation("修改服务器列表")
    @PostMapping (value = "/updateServerlist")
    public R updateServerlist(@RequestBody Serverlist serverlist) {
        QueryWrapper<Serverlist> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", serverlist.getId());
        Boolean sl = serverlistService.update(serverlist,queryWrapper);
        if (!sl) {
            return R.error("修改失败");
        }
        return R.ok();
    }

    /**
     * 添加服务器列表
     * @param Serverlist
     * @return
     */
    @ApiOperation("添加服务器列表")
    @PostMapping("/saveServerlist")
    public R saveServerlist(@RequestBody Serverlist Serverlist) {
        Boolean si = serverlistService.save(Serverlist);
        if (!si) {
            return R.error("添加失败");
        }
        return R.ok(Serverlist);
    }

    /**
     * 删除服务器列表
     * @param id
     * @return
     */
    @ApiOperation("删除服务器列表")
    @GetMapping  (value = "/deleteServerlist")
    public R deleteServerlist(Integer id) {
        Boolean pi =  serverlistService.removeById(id);
        if (!pi) {
            return R.error("删除服务器列表");
        }
        return R.ok();
    }
}

