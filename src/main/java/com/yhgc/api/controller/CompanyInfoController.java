package com.yhgc.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yhgc.api.entity.CompanyInfo;
import com.yhgc.api.service.UserinfoLoginToken;
import com.yhgc.api.util.R;
import com.yhgc.api.vo.UserInfo3Vo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

/**
 * <p>
 * 单位基本信息 前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-25
 */
@Api(tags = "单位基本信息")
@RestController
@RequestMapping("/companyInfo")
@Transactional
public class CompanyInfoController extends BaseController{


    /**
     * 查询单位基本信息
     * @return
     */
    @ApiOperation("查询单位基本信息")
    @GetMapping(value = "/queryCompanyInfo")
    @UserinfoLoginToken
    public R queryCompanyInfo() {
        Map<String,Object> map = new HashMap<>();
        QueryWrapper<CompanyInfo> queryWrapper = new QueryWrapper<>();
        UserInfo3Vo userInfo= (UserInfo3Vo) redisTemplate.opsForValue().get("userInfo");
        queryWrapper.eq("CompId",userInfo.getCompId());
        CompanyInfo companyInfo = companyInfoService.getOne(queryWrapper);
        map.put("CompanyInfo",companyInfo);
        return R.ok(map);
    }

    /**
     *  修改单位基本信息
     * @param companyInfo
     * @return
     */
    @ApiOperation("修改单位基本信息")
    @PostMapping(value = "/updateCompanyInfo")
    @UserinfoLoginToken
    public R updateCompanyInfo(CompanyInfo companyInfo, @RequestParam(value = "licenses",required=false) MultipartFile licenses) {
        String s = "";
        if(licenses!=null){
            s  = saveFile(licenses);
            companyInfo.setLicense(s);
        }else {
            companyInfo.setLicense("");
        }
        QueryWrapper<CompanyInfo> query = new QueryWrapper<>();
        query.eq("Id",companyInfo.getId());
        Boolean dt = companyInfoService.update(companyInfo,query);
        if (!dt) {
            return R.error("更新失败");
        }
        return R.ok("更新成功!");
    }

    private String saveFile(MultipartFile file) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                Set<String> typeSet = new HashSet<>();
                typeSet.add(".jpg");
                typeSet.add(".png");
                typeSet.add(".gif");
                String originalFileName = file.getOriginalFilename();
                String suffix = originalFileName.substring(originalFileName.lastIndexOf('.'));
                if(!typeSet.contains(suffix)){
                    return null;
                }
                //2、使用UUID生成新文件名
                String newFileName = UUID.randomUUID() + suffix;

                String filePath = "C:\\data\\payment-dome\\src\\main\\webapps\\upload\\" + newFileName;
                File saveDir = new File(filePath);
                if (!saveDir.getParentFile().exists())
                    saveDir.getParentFile().mkdirs();
                // 转存文件
                file.transferTo(saveDir);

                String fileName="http://192.168.10.111:8081/upload/"+newFileName;
                return fileName;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return "";
        }
        return "";
    }
}

