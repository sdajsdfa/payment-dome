package com.yhgc.api.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.entity.*;
import com.yhgc.api.service.UserinfoLoginToken;
import com.yhgc.api.util.R;
import com.yhgc.api.vo.MachineVo;
import com.yhgc.api.vo.UserInfo3Vo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.*;

/**
 * <p>
 * 仪器 前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-24
 */
@Api(tags = "仪器")
@RestController
@RequestMapping("/machine")
@Transactional
public class MachineController extends BaseController {

    /**
     * 分页查询全部设备管理
     *
     * @return
     */
    @ApiOperation("分页查询全部设备管理")
    @GetMapping(value = "/pageQueryAllMachine")
    @UserinfoLoginToken
    public R pageQueryAllMachine(Integer pageNum, Integer pageSize, String query) {
        Map<String, Object> map = new HashMap<>();
        if(pageNum==null||pageSize==null){
            pageNum=1;
            pageSize=10;
        }
        Page<MachineVo> page = new Page<>(pageNum, pageSize);
        UserInfo3Vo userInfo= (UserInfo3Vo) redisTemplate.opsForValue().get("userInfo");
        IPage<MachineVo> iPage = machineService.pageQueryAllMachine(page,query,userInfo.getCompId());
        map.put("Machine", iPage);
        return R.ok(map);
    }

    /**
     * 根据id查询设备管理
     *
     * @param id
     * @return
     */
    @ApiOperation("根据id查询设备管理")
    @GetMapping(value = "/queryMachine")
    @UserinfoLoginToken
    public R queryMachine(Integer id) {
        Map<String, Object> map = new HashMap<>();
        Machine machine = machineService.getById(id);
        if(machine.getId()!=null){
            QueryWrapper<Verification> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("MachineId",machine.getMachineId());
            List<Verification> verifications = verificationService.list(queryWrapper);
            map.put("Verification",verifications);
        }
        map.put("MachineDetailed",machine);
        return R.ok(map);
    }

    /**
     * 添加和修改设备管理
     *
     * @param machine
     * @return
     */
    @ApiOperation(value = "添加和修改设备管理",httpMethod = "POST")
    @PostMapping(value = "/addUpdateMachine")
    @UserinfoLoginToken
    public R addUpdateMachine(String machine,String verification,@RequestParam("verifImages") MultipartFile[] verifImages) {
        Machine machines = JSON.parseObject(machine, Machine.class);
        Verification verifications = JSON.parseObject(verification, Verification.class);
        if(machines.getId()<0){
            QueryWrapper<Machine> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("MachineId", machines.getMachineId());
            Machine machineOne = machineService.getOne(queryWrapper);
            if(machineOne!=null){
                return R.error("出厂编号已经存在");
            }
            machines.setJdStatus(0);
            UserInfo3Vo userInfo= (UserInfo3Vo) redisTemplate.opsForValue().get("userInfo");
            machines.setCompId(userInfo.getCompId());
            machines.setValid(1);
            Boolean ui = machineService.save(machines);
            if (!ui) {
                return R.error("添加设备管理失败");
            }
            if(verifications!=null){
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
                    verifications.setVerifImage(StringUtils.join(Arrays.asList(list.toArray()),", "));
                }
                verifications.setMachineId(machines.getMachineId());
                Boolean vi = verificationService.save(verifications);
                if (!vi) {
                    return R.error("添加设备管理失败");
                }
            }
            return R.ok("添加设备管理成功");
        }else {
            QueryWrapper<Machine> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("Id", machines.getId());
            Boolean p = machineService.update(machines,queryWrapper);
            if (!p) {
                return R.error("修改设备管理失败");
            }
            return R.ok("修改设备管理成功");
        }
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


    /**
     * 根据id删除设备管理
     *
     * @param id
     * @return
     */
    @ApiOperation("根据id删除设备管理")
    @GetMapping(value = "/deleteMachine")
    @UserinfoLoginToken
    public R deleteMachine(Integer id) {
//        Machine machine = machineService.getById(id);
//        System.out.println(machine);
//        if(machine.getMachineId()!=null){
//            QueryWrapper<Verification> queryWrapper = new QueryWrapper<>();
//            queryWrapper.eq("MachineId", machine.getMachineId());
//            List<Verification> verification = verificationService.list(queryWrapper);
//            Boolean p = machineService.removeByIds(verification);
//            if (!p) {
//                return R.error("删除设备管理失败");
//            }
//        }
        Boolean pi = machineService.removeById(id);
        if (!pi) {
            return R.error("删除设备管理失败");
        }
        return R.ok("删除设备管理成功");
    }

}

