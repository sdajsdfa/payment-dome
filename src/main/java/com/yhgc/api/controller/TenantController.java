package com.yhgc.api.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.commom.Const;
import com.yhgc.api.entity.*;
import com.yhgc.api.service.UserinfoLoginToken;
import com.yhgc.api.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 租户 前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2023-06-07
 */
@Api(tags = "租户")
@RestController
@RequestMapping("/tenant")
public class TenantController extends BaseController {

    /**
     * 分页查询全部租户
     *
     * @return
     */
    @ApiOperation("分页查询全部工程信息")
    @GetMapping(value = "/pageQueryAllTenant")
    @UserinfoLoginToken
    public R pageQueryAllTenant(Integer pageNum, Integer pageSize, String query) {
        Map<String, Object> map = new HashMap<>();
        if(pageNum==null||pageSize==null){
            pageNum=1;
            pageSize=10;
        }
        Page<Tenant> page = new Page<>(pageNum, pageSize);
        IPage<Tenant> iPage = tenantService.pageQueryAllTenant(page,query);
        map.put("Tenant", iPage);
        return R.ok(map);
    }


    @ApiOperation(value = "添加和修改工程信息",httpMethod = "POST")
    @PostMapping(value = "/addUpdateTenant")
    @UserinfoLoginToken
    public R addUpdateTenant(@RequestBody Tenant tenant) {
        if(tenant.getId()<0){
            String compId = "T" + tenant.getProvinceCode();
            String Number ="";
            List<Tenant> list = tenantService.selectTenantCode(compId);
            if(list.size() > 0){
                int count = list.size();
                String d =list.get(count-1).getCompId();
                int intNumber = Integer.parseInt(d.substring(3,7));
                intNumber++;
                Number = String.valueOf(intNumber);
                for (int i = 0; i < 4; i++){
                    Number = Number.length() < 4 ? "0" + Number : Number;
                }
                Number = "T" + tenant.getProvinceCode() + Number;
            }else{
                Number = "T" +tenant.getProvinceCode() + "0001";
            }

            QueryWrapper<Tenant> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("CompId", tenant.getCompId());
            Tenant tenantOne = tenantService.getOne(queryWrapper);
            if(tenantOne!=null){
                R.error("添加租户标识已存在");
            }
            tenant.setCreateTime(new Date());
            tenant.setCompId(Number);
            tenant.setValid(0);
            tenantService.save(tenant);

            CompanyInfo companyInfo = new CompanyInfo();
            companyInfo.setCompId(tenant.getCompId());
            companyInfo.setCompName(tenant.getTenantName());
            companyInfo.setCreateDate(new Date());
            companyInfoService.save(companyInfo);

            RoleInfo roleInfo = new RoleInfo();
            roleInfo.setCompId(tenant.getCompId());
            roleInfo.setRoleName(Const.DEFULT_ACCOUNT);
            roleInfo.setCreateDate(new Date());
            roleInfo.setIsAdmin(0);
            roleInfo.setCompId(tenant.getCompId());
            roleInfo.setRoleAccess("{\"ProjectManage\":1,\"ProjectRegister\":1,\"ProjectQuery\":1,\"ProjectAdd\":1,\"ProjectEdite\":1,\"TaskInfo\":1,\"TaskQuery\":1,\"TaskAdd\":1,\"TaskEdite\":1,\"TaskDelete\":1,\"TestManage\":1,\"SceneInfo\":1,\"TestData\":1,\"MachineManage\":1,\"MachineInfo\":1,\"MachineQuery\":1,\"MachineAdd\":1,\"MachineVerif\":1,\"MachineEdite\":1,\"MachineDelete\":1,\"TenantConfiguration\":1,\"Tenant\":1,\"TenantQuery\":1,\"TenantAdd\":1,\"TenantEdite\":1,\"TenantDelete\":1,\"SystemManage\":1,\"CompInfo\":1,\"CompInfoLook\":1,\"CompInfoEdit\":1,\"Struct\":1,\"User\":1,\"UserQuery\":1,\"UserAdd\":1,\"UserResetPassword\":1,\"UserEdite\":1,\"UserDelete\":1,\"Role\":1,\"RoleAdd\":1,\"RoleEdite\":1,\"RoleDelete\":1}");
            roleInfoService.save(roleInfo);

            Access access = JSON.parseObject("{\"ProjectManage\":1,\"ProjectRegister\":1,\"ProjectQuery\":1,\"ProjectAdd\":1,\"ProjectEdite\":1,\"TaskInfo\":1,\"TaskQuery\":1,\"TaskAdd\":1,\"TaskEdite\":1,\"TaskDelete\":1,\"TestManage\":1,\"SceneInfo\":1,\"TestData\":1,\"MachineManage\":1,\"MachineInfo\":1,\"MachineQuery\":1,\"MachineAdd\":1,\"MachineVerif\":1,\"MachineEdite\":1,\"MachineDelete\":1,\"TenantConfiguration\":1,\"Tenant\":1,\"TenantQuery\":1,\"TenantAdd\":1,\"TenantEdite\":1,\"TenantDelete\":1,\"SystemManage\":1,\"CompInfo\":1,\"CompInfoLook\":1,\"CompInfoEdit\":1,\"Struct\":1,\"User\":1,\"UserQuery\":1,\"UserAdd\":1,\"UserResetPassword\":1,\"UserEdite\":1,\"UserDelete\":1,\"Role\":1,\"RoleAdd\":1,\"RoleEdite\":1,\"RoleDelete\":1}", Access.class);
            access.setRoleId(roleInfo.getId());
            accessService.save(access);

            UserInfo2 userInfo2 = new UserInfo2();
            String md5Password = getMd5(Const.DEFULT_PASSWORD,Number);
            userInfo2.setPassword(md5Password);
            userInfo2.setAccount(Number);
            userInfo2.setFromRoleId(roleInfo.getId());
            userInfo2.setCompId(tenant.getCompId());
            userInfo2.setValid(0);
            userInfo2.setName("李");
            userInfo2Service.save(userInfo2);

            return R.ok("添加租户成功");
        }else {
            tenantService.updateById(tenant);
            Tenant tenantOne = tenantService.getById(tenant.getId());
            QueryWrapper<CompanyInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("CompId", tenantOne.getCompId());
            CompanyInfo companyInfo =companyInfoService.getOne(queryWrapper);
            companyInfo.setCompName(tenant.getTenantName());
            companyInfoService.updateById(companyInfo);
            return R.ok("修改租户成功");
        }

    }

    //md5加密规则
    private String getMd5(String password, String account) {
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((account + password + account).getBytes()).toUpperCase();
        }
        return password;
    }

    /**
     *  删除租户
     * @param id
     * @return
     */
    @ApiOperation("删除租户")
    @GetMapping("/deleteTenant")
    @UserinfoLoginToken
    public R deleteTenant(Integer id) {
        Tenant  tenant= tenantService.getById(id);
        tenant.setValid(1);
        Boolean t =tenantService.updateById(tenant);
        if (!t) {
            return R.error("删除租户失败");
        }
        return R.ok("删除租户成功");
    }

    /**
     * 根据ID查询租户
     * @param id
     * @return
     */
    @ApiOperation("根据ID查询租户")
    @GetMapping(value = "/queryTenant")
    @UserinfoLoginToken
    public R queryTenant(Integer id) {
        Map<String, Object> map = new HashMap<>();
        Tenant tenant = tenantService.getById(id);
        map.put("TenantDetailed",tenant);
        return R.ok(map);
    }


}

