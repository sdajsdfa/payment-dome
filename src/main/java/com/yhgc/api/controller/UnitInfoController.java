package com.yhgc.api.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.entity.UnitInfo;
import com.yhgc.api.entity.UserInfo;
import com.yhgc.api.service.UnitInfoService;
import com.yhgc.api.service.UserinfoLoginToken;
import com.yhgc.api.service.UserInfoService;
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
@RequestMapping("/unitInfo")
public class UnitInfoController {

    @Resource
    private UnitInfoService unitinfoService;

    @Resource
    private UserInfoService userinfoService;

    
    /**
     *查询所有单位
     * @return
     */
    @UserinfoLoginToken
    @ApiOperation("查询所有单位")
    @PostMapping(value = "/queryAllUnitInfo")
    public R queryAllUnitInfo() {
        Page<UnitInfo> page = new Page<>(1,10);
        //根据条件查询数据
        QueryWrapper<UnitInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status",0);
        IPage<UnitInfo> iPage = unitinfoService.page(page, queryWrapper);
        return R.ok(iPage);
    }

    /**
     *  添加单位信息
     * @param unitinfo
     * @return
     */
    @ApiOperation("添加单位信息")
    @PostMapping(value = "/saveAndUpdate")
    public R addUnitInfo(@RequestBody UnitInfo unitinfo) {
        String unitName =unitinfo.getUnitName();
        QueryWrapper<UnitInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("unitName",unitName);
        UnitInfo u = unitinfoService.getOne(queryWrapper);
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
    public R addUnitAdmin(@RequestBody UserInfo userinfo) {
        String account = userinfo.getAccount();
        String password = userinfo.getPassword();
        String idCard = userinfo.getIdCard();
        if (account==null || password==null || idCard==null) {
            R.error("单位管理员信息不能为空");
        }
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
//      queryWrapper.eq("account",account);
        queryWrapper.eq("idCard",idCard);
        UserInfo u =  userinfoService.getOne(queryWrapper);
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
            UnitInfo unitinfo = new UnitInfo();
            //将实体对象进行包装，包装为操作条件
            unitinfo.setStatus(2);
            QueryWrapper<UnitInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id",id);
            Boolean ui =  unitinfoService.update(unitinfo,queryWrapper);
            if (!ui) {
                return R.error("删除单位信息失败");
            }
            QueryWrapper<UserInfo> query = new QueryWrapper<>();
            query.eq("unitId",id);
            List<UserInfo> list = userinfoService.list(query);
            for (UserInfo userinfo:list) {
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

