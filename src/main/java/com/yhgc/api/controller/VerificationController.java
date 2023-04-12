package com.yhgc.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.deploy.util.StringUtils;
import com.yhgc.api.entity.DeviceManagement;
import com.yhgc.api.entity.ProjectInfo;
import com.yhgc.api.entity.Verification;
import com.yhgc.api.service.DeviceManagementService;
import com.yhgc.api.service.UserinfoLoginToken;
import com.yhgc.api.service.VerificationService;
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
 * @since 2023-03-28
 */
@Api(tags = "检定信息")
@RestController
@RequestMapping("/verification")
public class VerificationController {

    @Resource
    private VerificationService verificationService;

    @Resource
    private DeviceManagementService deviceManagementService;


    /**
     * 查询检定信息
     *
     * @param id
     * @return
     */
    @ApiOperation("查询检定信息")
    @GetMapping(value = "/queryVerification")
    public R queryVerification(Long id) {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Verification> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sbId", id);
        List<Verification> verification = verificationService.list(queryWrapper);
        map.put("verification",verification);
        return R.ok(map);
    }

    /**
     * 添加检定信息
     *
     * @param verification
     * @return
     */
    @ApiOperation(value = "添加检定信息",httpMethod = "POST")
    @PostMapping(value = "/addVerification")
    public R addVerification(Verification verification,@RequestParam("files")MultipartFile[] files, HttpServletRequest request) {
        List list = new ArrayList();
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                // 保存文件
                list = saveFile(request, file, list);
                if(list==null){
                    return R.error("上传类型错误");
                }
           }
        }
        verification.setPicture(StringUtils.join(Arrays.asList(list.toArray()),", "));
        Boolean p = verificationService.saveVerification(verification);
        DeviceManagement deviceManagement=deviceManagementService.getById(verification.getSbId());
        deviceManagement.setJdStatus(1);
        deviceManagement.setSbStatus(1);
        QueryWrapper<DeviceManagement> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", deviceManagement.getId());
        Boolean d = deviceManagementService.update(deviceManagement,queryWrapper);
        return R.ok("添加检测信息成功");
    }

    private List saveFile(HttpServletRequest request, MultipartFile file, List list) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                // 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中// )
//                String filePath = request.getSession().getServletContext()
//                        .getRealPath("/")
//                        + "upload/" + file.getOriginalFilename();
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
                list.add("http://192.168.10.111:8081/upload/"+newFileName);
                File saveDir = new File(filePath);
                if (!saveDir.getParentFile().exists())
                    saveDir.getParentFile().mkdirs();
                // 转存文件
                file.transferTo(saveDir);
                return list;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            return null;
        }
        return list;
    }

}

