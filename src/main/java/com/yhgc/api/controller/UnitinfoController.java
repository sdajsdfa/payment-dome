package com.yhgc.api.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.entity.Unitinfo;
import com.yhgc.api.entity.Userinfo;
import com.yhgc.api.service.UnitinfoService;
import com.yhgc.api.service.UserinfoLoginToken;
import com.yhgc.api.service.UserinfoService;
import com.yhgc.api.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * <p>
 * 单位信息 前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-27
 */
@Api(tags = "单位信息")
@RestController
@RequestMapping("/unitinfo")
public class UnitinfoController {

    @Resource
    private UnitinfoService unitinfoService;

    @Resource
    private UserinfoService userinfoService;

    
    /**
     *查询所有单位
     * @return
     */
    @UserinfoLoginToken
    @ApiOperation("查询所有单位")
    @PostMapping(value = "/queryAllUnitInfo")
    public R queryAllUnitInfo() {
        Page<Unitinfo> page = new Page<>(1,10);
        //根据条件查询数据
        QueryWrapper<Unitinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status",0);
        IPage<Unitinfo> iPage = unitinfoService.page(page, queryWrapper);
        return R.ok(iPage);
    }

    /**
     *  添加单位信息
     * @param unitinfo
     * @return
     */
    @ApiOperation("添加单位信息")
    @PostMapping(value = "/saveAndUpdate")
    public R addUnitInfo(@RequestBody Unitinfo unitinfo) {
        String unitName =unitinfo.getUnitName();
        QueryWrapper<Unitinfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("unitName",unitName);
        Unitinfo u = unitinfoService.getOne(queryWrapper);
        if (u != null){
            return R.error("单位名称已经被注册");
        }
        unitinfo.setCreateTime(new Date());
        unitinfo.setStatus(0);
        Boolean ui = unitinfoService.save(unitinfo);
        if (!ui) {
            return R.error("添加失败");
        }
        HashMap<String,Object> hashMap =new HashMap<>();
        hashMap.put("unitId",unitinfo.getId());
        return R.ok(hashMap);
    }

    /**
     * 添加单位管理员
     * @param userinfo
     * @return
     */
    @ApiOperation("添加单位管理员")
    @PostMapping(value = "/addUnitAdmin")
    public R addUnitAdmin(@RequestBody Userinfo userinfo) {
        String account = userinfo.getAccount();
        String password = userinfo.getPassword();
        String idCard = userinfo.getIdCard();
        if (account==null || password==null || idCard==null) {
            R.error("单位管理员信息不能为空");
        }
        QueryWrapper<Userinfo> queryWrapper = new QueryWrapper<>();
//      queryWrapper.eq("account",account);
        queryWrapper.eq("idCard",idCard);
        Userinfo u =  userinfoService.getOne(queryWrapper);
        if (u != null){
            return R.error("身份证已经被注册");
        }
        userinfo.setCreateTime(new Date());
        userinfo.setStatus(0);
        Boolean ui = userinfoService.save(userinfo);
        if (!ui) {
            return R.error("添加失败");
        }
        return R.ok(userinfo);
    }

    /**
     * 删除单位信息
     * @param id
     * @return
     */
    @ApiOperation("删除单位信息")
    @GetMapping(value = "/deleteUnitInfo")
    public R deleteUnitInfo(Long id) {
        try{
            Unitinfo unitinfo = new Unitinfo();
            //将实体对象进行包装，包装为操作条件
            unitinfo.setStatus(2);
            QueryWrapper<Unitinfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",id);
            Boolean ui =  unitinfoService.update(unitinfo,queryWrapper);
            if (!ui) {
                return R.error("删除单位信息失败");
            }
            QueryWrapper<Userinfo> query = new QueryWrapper<>();
            query.eq("unitId",id);
            List<Userinfo> list = userinfoService.list(query);
            for (Userinfo userinfo:list) {
                userinfo.setStatus(2);
                Boolean user =  userinfoService.update(userinfo,query);
                if (!user) {
                    return R.error("删除单位信息失败");
                }
            }
        }catch (Exception e){
            return R.error("删除失败");
        }
        return R.ok();
    }
}

