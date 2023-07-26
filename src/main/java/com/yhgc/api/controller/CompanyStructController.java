package com.yhgc.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yhgc.api.entity.CompanyStruct;
import com.yhgc.api.service.UserinfoLoginToken;
import com.yhgc.api.util.R;
import com.yhgc.api.vo.CompanyInfoVo;
import com.yhgc.api.vo.CompanyStructVo;
import com.yhgc.api.vo.UserInfo3Vo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * <p>
 * 组织架构 前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-25
 */
@Api(tags = "组织机构")
@RestController
@RequestMapping("/companyStruct")
@Transactional
public class CompanyStructController extends  BaseController{



    /**
     *  查询所有组织机构名称
     * @return
     */
    @ApiOperation("查询所有组织机构名称")
    @GetMapping(value = "/queryAllCompanyStruct")
    @UserinfoLoginToken
    public R queryAllCompanyStruct() {
        Map<String,Object> map = new HashMap<>();
        UserInfo3Vo userInfo= (UserInfo3Vo) redisTemplate.opsForValue().get("userInfo");
//        String CompId="T420020";
        // 模拟从数据库取数据
        CompanyInfoVo rootCompanyStruct = companyInfoService.companyStructList(userInfo.getCompId());
        map.put("CompanyInfo",rootCompanyStruct);
        return R.ok(map);
    }


    /**
     * 根据Id查询组织机构
     */
    @ApiOperation("根据Id查询组织机构")
    @GetMapping(value = "/queryCompanyStruct")
    @UserinfoLoginToken
    public R queryCompanyStruct(Integer id) {
        Map<String,Object> map = new HashMap<>();
        CompanyStruct companyStruct = companyStructService.getById(id);
        map.put("CompanyStruct",companyStruct);
        return R.ok(map);
    }


    /**
     *  添加和修改组织机构
     * @param companyStruct
     * @return
     */
    @ApiOperation("添加和修改组织机构")
    @PostMapping(value = "/addUpdateCompanyStruct")
    @UserinfoLoginToken
    public R saveUpdateCompanyStruct(@RequestBody CompanyStruct companyStruct) {
        if(companyStruct.getId()<1){
//            CompanyStruct dt =companyStructService.getById(companyStruct.getCompId());
//            if(dt==null){
//                return R.error("不可以添加");
//            }
            QueryWrapper<CompanyStruct> query = new QueryWrapper<>();
            query.eq("DepartName",companyStruct.getDepartName());
            CompanyStruct cs = companyStructService.getOne(query);
            if(cs!=null){
                return R.error("已存在名为"+cs.getDepartName()+"的组织.无法在一级别创建相同名称的组织单位.");
            }
            UserInfo3Vo userInfo= (UserInfo3Vo) redisTemplate.opsForValue().get("userInfo");
            companyStruct.setCompId(userInfo.getCompId());
            companyStruct.setValid(0);
            Boolean si = companyStructService.save(companyStruct);
            if (!si) {
                return R.error("添加失败");
            }
            return R.ok("添加成功!");
        }else {
            Boolean cs = companyStructService.updateById(companyStruct);
            if (!cs) {
                return R.error("更新失败");
            }
            return R.ok("更新成功!");
        }
    }

    /**
     * 删除组织机构
     * @param id
     * @return
     */
    @ApiOperation("删除组织机构")
    @GetMapping(value = "/deleteCompanyStruct")
    @UserinfoLoginToken
    public R deleteCompanyStruct(Integer id) {
        //将实体对象进行包装，包装为操作条件
        Boolean ui =  companyStructService.removeById(id);
        if (!ui) {
            return R.error("删除组织机构失败!");
        }
        return R.ok("删除组织机构成功");
    }

    /**
     * 查询所有部门名称
     */
    @ApiOperation("查询所有部门名称")
    @GetMapping(value = "/queryDepartName")
    public R companyStruct() {
        Map<String, Object> map = new HashMap<>();
        List<CompanyStructVo> roleInfo = companyStructService.queryDepartName();
        map.put("CompanyStruct",roleInfo);
        return R.ok(map);
    }

}

