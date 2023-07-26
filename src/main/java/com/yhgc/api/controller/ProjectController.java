package com.yhgc.api.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.entity.*;
import com.yhgc.api.service.UserinfoLoginToken;
import com.yhgc.api.util.R;
import com.yhgc.api.vo.ProjectNameVo;
import com.yhgc.api.vo.ProjectVo;
import com.yhgc.api.vo.UserInfo3Vo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 工程 前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-23
 */
@Api(tags = "工程")
@RestController
@RequestMapping("/project")
@Transactional
public class ProjectController extends BaseController {


    /**
     * 分页查询全部工程信息
     *
     * @return
     */
    @ApiOperation("分页查询全部工程信息")
    @GetMapping(value = "/pageQueryAllProject")
    @UserinfoLoginToken
    public R pageQueryAllProject(Integer pageNum, Integer pageSize, String query) {
        Map<String, Object> map = new HashMap<>();
        if(pageNum==null||pageSize==null){
            pageNum=1;
            pageSize=10;
        }
        Page<ProjectVo> page = new Page<>(pageNum, pageSize);
        UserInfo3Vo userInfo= (UserInfo3Vo) redisTemplate.opsForValue().get("userInfo");
        IPage<ProjectVo> iPage = projectService.pageQueryAllProject(page,query,userInfo.getCompId());
        map.put("Project", iPage);
        return R.ok(map);
    }


    @ApiOperation(value = "添加和修改工程信息",httpMethod = "POST")
    @PostMapping(value = "/addUpdateProject")
    @UserinfoLoginToken
    public R addUpdateProject(String project, String test, @RequestParam("layouts") MultipartFile[] layouts, @RequestParam("geology") MultipartFile[] geology) {
        Project projects = JSON.parseObject(project, Project.class);
        List<Test> testList = JSON.parseArray(test, Test.class);
        List list = new ArrayList();
        if (layouts != null && layouts.length > 0) {
            for (int i = 0; i < layouts.length; i++) {
                MultipartFile file = layouts[i];
                // 保存文件
                list = saveFile(file, list);
                if(list==null){
                    return R.error("上传类型错误");
                }
            }
            projects.setLayout(StringUtils.join(Arrays.asList(list.toArray()),", "));
        }
        List list2 = new ArrayList();
        if (geology != null && geology.length > 0) {
            for (int i = 0; i < geology.length; i++) {
                MultipartFile file = geology[i];
                // 保存文件
                list2 = saveFile(file, list2);
                if(list2==null){
                    return R.error("上传类型错误");
                }
            }
            projects.setGeology(StringUtils.join(Arrays.asList(list2.toArray()),", "));
        }
        if(projects.getId()<0){
            QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("ProjectName",projects.getProjectName());
            Project projectOne = projectService.getOne(queryWrapper);
            if(projectOne!=null){
                return R.error("该工程名称已经存在！");
            }
            if(projects.getIntervene()==0){
                projects.setFilingStatus(0);
            }
            if(projects.getIntervene()==1){
                projects.setFilingStatus(1);
            }
           try{
                projects.setCreationTime(new Date());
                UserInfo3Vo userInfo= (UserInfo3Vo) redisTemplate.opsForValue().get("userInfo");
                projects.setCompId(userInfo.getCompId());
                Boolean ui = projectService.save(projects);
                if (!ui) {
                    return R.error("添加工程失败");
                }
                if(testList!=null){
//                    for (Test t: testList) {
//                        String Number ="";
//                        SimpleDateFormat f = new SimpleDateFormat("yyyy");
//                        String date = f.format(new Date(System.currentTimeMillis()));
//                        List<Test> list3 = testService.selectTestDY();
//                        if(list3.size() > 0){
//                            int count = list3.size();
//                            String d =list3.get(count-1).getSerialNo();
//                            int intNumber = Integer.parseInt(d.substring(6,10));
//                            intNumber++;
//                            Number = String.valueOf(intNumber);
//                            for (int i = 0; i < 4; i++){
//                                Number = Number.length() < 4 ? "0" + Number : Number;
//                            }
//                            Number = "W" + date+ "-" + Number + "DY";
//                        }else{
//                            Number = "W" + date + "-"+ "0001DY";
//                        }
//                        t.setSerialNo(Number);
//                        t.setCompId(userInfo.getCompId());
//                        t.setTestStatus(0);
//                        t.setCreationTime(new Date());
//                        t.setProjectId(Math.toIntExact(projects.getId()));
//                        testService.save(t);
//                    }

                    for (int j =0;j<testList.size();j++){
                       if(testList.get(j).getTestMethod().equals("低应变")){
                           System.out.println("低应变000000000000000000000000000000");
                           String Number ="";
                           SimpleDateFormat f = new SimpleDateFormat("yyyy");
                           String date = f.format(new Date(System.currentTimeMillis()));
                           List<Test> listDY = testService.selectTestDY();
                           if(listDY.size() > 0){
                               int count = listDY.size();
                               String d =listDY.get(count-1).getSerialNo();
                               int intNumber = Integer.parseInt(d.substring(6,10));
                               intNumber++;
                               Number = String.valueOf(intNumber);
                               for (int i = 0; i < 4; i++){
                                   Number = Number.length() < 4 ? "0" + Number : Number;
                               }
                               Number = "W" + date+ "-" + Number + "DY";
                           }else{
                               Number = "W" + date + "-"+ "0001DY";
                           }
                           testList.get(j).setSerialNo(Number);
                           UserInfo3Vo userInfoDY= (UserInfo3Vo) redisTemplate.opsForValue().get("userInfo");
                           testList.get(j).setTestStatus(0);
                           testList.get(j).setCompId(userInfoDY.getCompId());
                           testList.get(j).setCreationTime(new Date());
                           testList.get(j).setProjectId(projects.getId());
                           testService.save(testList.get(j));
                       }else if(testList.get(j).getTestMethod().equals("高应变")){
                           System.out.println("高应变000000000000000000000");
                           String Number ="";
                           SimpleDateFormat f = new SimpleDateFormat("yyyy");
                           String date = f.format(new Date(System.currentTimeMillis()));
                           List<Test> listGY = testService.selectTestGY();
                           if(listGY.size() > 0){
                               int count = listGY.size();
                               String d =listGY.get(count-1).getSerialNo();
                               int intNumber = Integer.parseInt(d.substring(6,10));
                               intNumber++;
                               Number = String.valueOf(intNumber);
                               for (int i = 0; i < 4; i++){
                                   Number = Number.length() < 4 ? "0" + Number : Number;
                               }
                               Number = "W" + date+ "-" + Number + "GY";
                           }else{
                               Number = "W" + date + "-"+ "0001GY";
                           }
                           testList.get(j).setSerialNo(Number);
                           testList.get(j).setCreationTime(new Date());
                           UserInfo3Vo userInfoGY= (UserInfo3Vo) redisTemplate.opsForValue().get("userInfo");
                           testList.get(j).setTestStatus(0);
                           testList.get(j).setCompId(userInfoGY.getCompId());
                           testList.get(j).setProjectId(projects.getId());
                           testService.save(testList.get(j));
                       } else if( testList.get(j).getTestMethod().equals("声波透射法")){
                           System.out.println("声波透射法000000000000000000000");
                           String Number ="";
                           SimpleDateFormat f = new SimpleDateFormat("yyyy");
                           String date = f.format(new Date(System.currentTimeMillis()));
                           List<Test> listSC = testService.selectTestSC();
                           if(listSC.size() > 0){
                               int count = list.size();
                               String d =listSC.get(count-1).getSerialNo();
                               int intNumber = Integer.parseInt(d.substring(6,10));
                               intNumber++;
                               Number = String.valueOf(intNumber);
                               for (int i = 0; i < 4; i++){
                                   Number = Number.length() < 4 ? "0" + Number : Number;
                               }
                               Number = "W" + date+ "-" + Number + "SC";
                           }else{
                               Number = "W" + date + "-"+ "0001SC";
                           }
                           testList.get(j).setSerialNo(Number);
                           testList.get(j).setCreationTime(new Date());
                           UserInfo3Vo userInfoSC= (UserInfo3Vo) redisTemplate.opsForValue().get("userInfo");
                           testList.get(j).setTestStatus(0);
                           testList.get(j).setCompId(userInfoSC.getCompId());
                           testList.get(j).setProjectId(projects.getId());
                           testService.save(testList.get(j));
                       }
                    }
                }
                return R.ok("添加工程成功");
            }catch (Exception e){
                 return R.error(e.getMessage());
            }
        }else {
            if(projects.getIntervene()==0){
                projects.setFilingStatus(1);
            }
            if(projects.getIntervene()==1){
                projects.setFilingStatus(2);
            }
            System.out.println(projects+"=================00000================");
            Boolean p = projectService.updateById(projects);
            if (!p) {
                return R.error("修改工程失败");
            }
            return R.ok("修改工程成功");
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
     * 查询工程详细信息
     * @param id
     * @return
     */
    @ApiOperation("查询工程详细信息")
    @GetMapping(value = "/queryProject")
    @UserinfoLoginToken
    public R queryProject(Integer id) {
        Map<String, Object> map = new HashMap<>();
        Project project = projectService.getById(id);
        if(project==null){
            return R.ok();
        }
        QueryWrapper<Test> methodInfoQueryWrapper = new QueryWrapper<>();
        methodInfoQueryWrapper.eq("ProjectId",project.getId());
        List<Test> testList = testService.list(methodInfoQueryWrapper);
        map.put("Project",project);
        map.put("TestList",testList);
        return R.ok(map);
    }

    /**
     * 查询所有工程名称
     * @return
     */
    @ApiOperation("查询所有工程名称")
    @PostMapping("/verificationAllProjectName")
    @UserinfoLoginToken
    public R verificationAllProjectName() {
        Map<String, Object> map = new HashMap<>();
        List<ProjectNameVo> projectNameVos =  projectService.verificationList();
        map.put("Project",projectNameVos);
        return R.ok(map);
    }

}