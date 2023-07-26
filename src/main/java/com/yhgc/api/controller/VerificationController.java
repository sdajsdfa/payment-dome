package com.yhgc.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yhgc.api.entity.Machine;
import com.yhgc.api.entity.Verification;
import com.yhgc.api.service.UserinfoLoginToken;
import com.yhgc.api.util.R;
import com.yhgc.api.vo.UserInfo3Vo;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

/**
 * <p>
 * 检定 前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-24
 */
@RestController
@RequestMapping("/verification")
@Transactional
public class VerificationController extends BaseController {

    /**
     * 查询检定信息
     *
     * @param id
     * @return
     */
    @ApiOperation("查询检定信息")
    @GetMapping(value = "/queryVerification")
    @UserinfoLoginToken
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
    @UserinfoLoginToken
    public R addVerification(Verification verification, @RequestParam("verifImages") MultipartFile[] verifImages) {
        List list = new ArrayList();
        if (verifImages != null && verifImages.length > 0) {
            for (int i = 0; i < verifImages.length; i++) {
                MultipartFile file = verifImages[i];
                // 保存文件
                list = saveFile(file, list);
                if(list==null){
                    return R.error("上传类型错误");
                }
            }
            verification.setVerifImage(StringUtils.join(Arrays.asList(list.toArray()),", "));
        }
        UserInfo3Vo userInfo= (UserInfo3Vo) redisTemplate.opsForValue().get("userInfo");
        verification.setCompId(userInfo.getCompId());
        Boolean p = verificationService.save(verification);
        QueryWrapper<Machine> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("MachineId", verification.getMachineId());
        Machine machine=machineService.getOne(queryWrapper1);
        machine.setStatus(1);
        machine.setJdStatus(1);
        QueryWrapper<Machine> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("Id", machine.getId());
        Boolean d = machineService.update(machine,queryWrapper);
        return R.ok("添加检测信息成功");
    }

    private List saveFile(MultipartFile file, List list) {
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

