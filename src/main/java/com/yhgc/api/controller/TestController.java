package com.yhgc.api.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.entity.Test;
import com.yhgc.api.service.UserinfoLoginToken;
import com.yhgc.api.util.R;
import com.yhgc.api.vo.TestVo;
import com.yhgc.api.vo.UserInfo3Vo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 检测任务 前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-23
 */
@Api(tags = "检测任务")
@RestController
@RequestMapping("/test")
@Transactional
public class TestController extends BaseController {


    /**
     * 分页查询全部检测任务
     *
     * @return
     */
    @ApiOperation("分页查询全部检测任务")
    @GetMapping(value = "/pageQueryAllTest")
    @UserinfoLoginToken
    public R pageQueryAllTest(Integer pageNum, Integer pageSize, String query) {
        Map<String, Object> map = new HashMap<>();
        if(pageNum==null||pageSize==null){
            pageNum=1;
            pageSize=10;
        }
        Page<TestVo> page = new Page<>(pageNum, pageSize);
        UserInfo3Vo userInfo= (UserInfo3Vo) redisTemplate.opsForValue().get("userInfo");
        IPage<TestVo> iPage = testService.pageQueryAllTest(page,query,userInfo.getCompId());
        map.put("Test", iPage);
        return R.ok(map);
    }


    /**
     * 添加和修改检测任务
     *
     * @param test
     * @return
     */
    @ApiOperation(value = "添加和修改检测任务",httpMethod = "POST")
    @PostMapping(value = "/addUpdateTest")
    @UserinfoLoginToken
    public R addUpdateTest(String test) {
        Test tests = JSON.parseObject(test, Test.class);
//        if(tests.getTester()!=null){
//            ProjectInfoVo projectInfoVo = JSON.parseObject(tests.getTester(), ProjectInfoVo.class);
//            tests.setTester(projectInfoVo.getProjectName());
//        }
        Map<String,Object> map = new HashMap<>();
        if(tests.getId()<0){
            if(tests.getTestMethod().equals("低应变")){
                String Number ="";
                SimpleDateFormat f = new SimpleDateFormat("yyyy");
                String date = f.format(new Date(System.currentTimeMillis()));
                List<Test> list = testService.selectTestDY();
                if(list.size() > 0){
                    int count = list.size();
                    String d =list.get(count-1).getSerialNo();
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
                tests.setSerialNo(Number);
            } else if(tests.getTestMethod().equals("高应变")){
                String Number ="";
                SimpleDateFormat f = new SimpleDateFormat("yyyy");
                String date = f.format(new Date(System.currentTimeMillis()));
                List<Test> list = testService.selectTestGY();
                if(list.size() > 0){
                    int count = list.size();
                    String d =list.get(count-1).getSerialNo();
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
                tests.setSerialNo(Number);
            } else if(tests.getTestMethod().equals("声波透射法")){
                String Number ="";
                SimpleDateFormat f = new SimpleDateFormat("yyyy");
                String date = f.format(new Date(System.currentTimeMillis()));
                List<Test> list = testService.selectTestSC();
                if(list.size() > 0){
                    int count = list.size();
                    String d =list.get(count-1).getSerialNo();
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
                tests.setSerialNo(Number);
            }
            tests.setCreationTime(new Date());
            UserInfo3Vo userInfo= (UserInfo3Vo) redisTemplate.opsForValue().get("userInfo");
            tests.setTestStatus(0);
            tests.setCompId(userInfo.getCompId());
            Boolean t = testService.save(tests);
            if (!t) {
                return R.error("添加检测任务失败");
            }
            return R.ok(map);
        }else {
            QueryWrapper<Test> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("Id", tests.getId());
            Boolean p = testService.update(tests,queryWrapper);
            if (!p) {
                return R.error("修改检测任务失败");
            }
              return R.ok("修改检测任务成功");
        }
    }

    /**
     * 根据ID查询检测任务
     * @param id
     * @return
     */
    @ApiOperation("根据ID查询检测任务")
    @GetMapping(value = "/queryTest")
//    @UserinfoLoginToken
    public R queryTest(Integer id) {
        Map<String, Object> map = new HashMap<>();
        TestVo test = testService.queryTest(id);
//        System.out.println(Arrays.toString(test.getTestBase().split("\\\\s+"))+"===============");
        map.put("TestDetailed",test);
        return R.ok(map);
    }

    /**
     *  删除检测项目
     * @param id
     * @return
     */
    @ApiOperation("删除检测任务")
    @GetMapping("/deleteTset")
    @UserinfoLoginToken
    public R deleteTset(Integer id) {
        Boolean  t= testService.removeById(id);
        if (!t) {
            return R.error("删除检测任务失败");
        }
        return R.ok("删除检测任务成功");
    }

    /**
     * 获取所得的检测方法
     */
    @ApiOperation("获取所得的检测方法")
    @GetMapping("/queryTestMethod")
    @UserinfoLoginToken
    public R queryTestMethod() {
        Map<String, String> map = new HashMap<>();
        return R.ok(map);
    }

}

