package com.yhgc.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yhgc.api.entity.Department;
import com.yhgc.api.entity.Unit;
import com.yhgc.api.service.UnitService;
import com.yhgc.api.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2023-04-23
 */
@Api(tags = "信息")
@RestController
@RequestMapping("/unit")
public class UnitController {

    @Resource
    private UnitService unitService;


    /**
     *查询
     * @return
     */
    @ApiOperation("查询id")
    @GetMapping(value = "/queryUnit")
    public R queryUnit() {
        Map<String,Object> map = new HashMap<>();
        QueryWrapper<Unit> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code","T4200020");
        Unit unit = unitService.getOne(queryWrapper);
        map.put("unit",unit);
        return R.ok(map);
    }

    /**
     *  添加和修改信息
     * @param unit
     * @return
     */
    @ApiOperation("修改信息")
    @PostMapping(value = "/updateUnit")
    public R updateUnit(Unit unit,HttpServletRequest request,MultipartFile files) {
        System.out.println(unit+"=================");
        String s = "";
        if(files!=null){
            s  = saveFile(request, files);
        }
        System.out.println(s+"======================");
        unit.setPhoto(s);
        unit.setRegDate(new Date());
        QueryWrapper<Unit> query = new QueryWrapper<>();
        query.eq("id",unit.getId());
        Boolean dt = unitService.updateUnit(unit);
        if (!dt) {
            return R.error("更新失败");
        }
        return R.ok("更新成功!");
    }

    private String saveFile(HttpServletRequest request, MultipartFile file) {
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

