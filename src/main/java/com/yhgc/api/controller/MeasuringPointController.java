package com.yhgc.api.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lkx.util.ExcelUtil;
import com.yhgc.api.entity.MeasuringPoint;
import com.yhgc.api.service.MeasuringPointService;
import com.yhgc.api.util.ExcelTool;
import com.yhgc.api.util.R;
import com.yhgc.api.vo.MeasuringPointVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2023-03-31
 */
@Api(tags = "测点信息")
@RestController
@RequestMapping("/measuring-point")
public class MeasuringPointController {

    @Resource
    private MeasuringPointService measuringPointService;


    /**
     * 查询检测方法分类
     *
     * @param id
     * @return
     */
    @ApiOperation("查询测点信息")
    @GetMapping(value = "/queryByMeasuringPoint")
    public R queryByMeasuringPoint(Long id) {
        Map<String, Object> map = new HashMap<>();
        MeasuringPointVo measuringPointVo = measuringPointService.getByIdMeasuringPoint(id);
        map.put("measuringPoint",measuringPointVo);
        return R.ok(map);
    }


    /**
     * 添加和修改设备管理
     *
     * @param measuringPoint
     * @return
     */
    @ApiOperation(value = "添加和修改设备管理",httpMethod = "POST")
    @PostMapping(value = "/addUpdateMeasuringPoint")
    public R addUpdateMeasuringPoint(@RequestBody MeasuringPoint measuringPoint) {
        Map<String,Object> map = new HashMap<>();
        if(measuringPoint.getMpId()<0){
            measuringPoint.setPileDate(new Date());
            Boolean ui = measuringPointService.saveMeasuringPoint(measuringPoint);
            if (ui) {
                return R.ok(map);
            }
            return R.error("添加测点信息失败");
        }else {
            QueryWrapper<MeasuringPoint> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", measuringPoint.getMpId());
            Boolean p = measuringPointService.update(measuringPoint,queryWrapper);
            if (!p) {
                return R.error("修改测点信息失败");
            }
            return R.ok("修改测点信息成功");
        }
    }

    /**
     * 批量保存
     *
     * @param list
     * @return
     */
    @ApiOperation(value = "批量保存",httpMethod = "POST")
    @PostMapping(value = "/saveBatchMeasuringPoint")
    public R saveBatchMeasuringPoint(@RequestBody List<MeasuringPoint> list) {
        System.out.println(list.size());
        for (int i=0;i<list.size();i++){
            if(list.get(i).getMpId()<0){
                boolean measuringPoints = measuringPointService.saveBatch(list);
                if(!measuringPoints){
                    return R.error("保存失败");
                }
                return R.ok("保存成功");
            } else {
//                QueryWrapper<MeasuringPoint> queryWrapper = new QueryWrapper<>();
//                queryWrapper.eq("mpId", list.get(i).getMpId());
                Boolean p = measuringPointService.updateBatchById(list);
                if(!p){
                    return R.error("修改失败");
                }
                return R.ok("修改成功");
            }
        }
        return R.ok();
    }


    /**
     * 批量作废
     *
     * @param idList
     * @return
     */
    @ApiOperation(value = "批量作废",httpMethod = "POST")
    @PostMapping(value = "/deleteBatchMeasuringPoint")
    public R deleteBatchMeasuringPoint(String[] idList) {
        boolean measuringPoints = measuringPointService.removeByIds(Arrays.asList(idList));
        if(!measuringPoints){
            return R.error("批量作废失败");
        }
        return R.ok("批量作废成功");
    }


    @ApiOperation(value = "导出")
    @GetMapping(value = "/exportMeasuringPoint")
    public void exportMeasuringPoint(@RequestParam(value="idList",required = true) String[] idList,HttpServletResponse response) throws Exception{
        List<MeasuringPoint> pageList;
        String filename ="W2023-005DY.xlsx";
        String[] heads = {"序号","桩号","桩径(mm)","施工桩长(m)","成桩日期","设计强度等级","承载力特征值(kN)","桩端持力层","设计桩长(m)","配桩情况(下+上)"};
        /**
         * 问题来了:我们从后台拿到的结果是   List<Employee>
         *                  工具要的效果  List<String[]>
         *                把List<Employee> -> List<String[]>
         */
        System.out.println(idList.length);
        if(idList.length>0){
            pageList  = measuringPointService.listByIds(Arrays.asList(idList));
        }else {
            pageList = null;
        }
        System.out.println(pageList);
//        pageList=null;
        List<String[]> list = new ArrayList<>();

        if(pageList!=null){
            //1.遍历List(拿到每一个员工对象)
            for (int i = 0; i < pageList.size(); i++) {
                MeasuringPoint measuringPoint = pageList.get(i);
                //2.定义一个数组
                String[] strs = new String[heads.length];
                //3.把 -》 strs
                strs[0] = measuringPoint.getMpId().toString();
                strs[1] = measuringPoint.getPileNo()==null?"":measuringPoint.getPileNo().toString();
                strs[2] = measuringPoint.getPileDiameter()==null?"":measuringPoint.getPileDiameter().toString();
                strs[3] = measuringPoint.getSgPileLength()==null?"":measuringPoint.getSgPileLength().toString();
                strs[4] = measuringPoint.getPileDate()==null?"":measuringPoint.getPileDate().toString();
                strs[5] = measuringPoint.getPowerLevel()==null?"":measuringPoint.getPowerLevel().toString();
                strs[6] = measuringPoint.getPileBearing()==null?"":measuringPoint.getPileBearing().toString();
                strs[7] = measuringPoint.getPileEndBearingLayer()==null?"":measuringPoint.getPileEndBearingLayer().toString();
                strs[8] = measuringPoint.getDesignPileLength()==null?"":measuringPoint.getDesignPileLength().toString();
                strs[9] = measuringPoint.getPzqk()==null?"":measuringPoint.getPzqk().toString();
                //4.把数组放到list中去
                list.add(strs);
            }
        }
        ExcelTool.export(filename, heads, list, response);
    }

    @GetMapping (value = "/importExcel")
    public R importExcel (@RequestParam("file") MultipartFile file) throws IOException {
        try {
            //从文件中读取Excel为ExcelReader
            List<MeasuringPoint> books = ExcelUtil.readXls(file.getBytes(),MeasuringPoint.class);
            System.out.println(books+"------");
            for (MeasuringPoint book:books) {
                book.setIsValid(139L);
                measuringPointService.save(book);
            }
            //插入到数据库
//            books.forEach(book ->
//                    measuringPointService.save(book));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return R.ok("导入成功");
    }

    @ApiOperation(value = "导出1")
    @GetMapping(value = "/exportMeasuringPoint1")
    public void exportMeasuringPoint1(HttpServletResponse response) throws Exception{
        ;
        String filename ="W2023-005DY.xlsx";
        String[] heads = {"序号","桩号","桩径(mm)","施工桩长(m)","成桩日期","设计强度等级","承载力特征值(kN)","桩端持力层","设计桩长(m)","配桩情况(下+上)"};
        /**
         * 问题来了:我们从后台拿到的结果是   List<Employee>
         *                  工具要的效果  List<String[]>
         *                把List<Employee> -> List<String[]>
         */
        List<MeasuringPoint> pageList =measuringPointService.list();
        List<String[]> list = new ArrayList<>();

        if(pageList!=null){
            //1.遍历List(拿到每一个员工对象)
            for (int i = 0; i < pageList.size(); i++) {
                MeasuringPoint measuringPoint = pageList.get(i);
                //2.定义一个数组
                String[] strs = new String[heads.length];
                //3.把 -》 strs
                strs[0] = measuringPoint.getMpId().toString();
                strs[1] = measuringPoint.getPileNo()==null?"":measuringPoint.getPileNo().toString();
                strs[2] = measuringPoint.getPileDiameter()==null?"":measuringPoint.getPileDiameter().toString();
                strs[3] = measuringPoint.getSgPileLength()==null?"":measuringPoint.getSgPileLength().toString();
                strs[4] = measuringPoint.getPileDate()==null?"":measuringPoint.getPileDate().toString();
                strs[5] = measuringPoint.getPowerLevel()==null?"":measuringPoint.getPowerLevel().toString();
                strs[6] = measuringPoint.getPileBearing()==null?"":measuringPoint.getPileBearing().toString();
                strs[7] = measuringPoint.getPileEndBearingLayer()==null?"":measuringPoint.getPileEndBearingLayer().toString();
                strs[8] = measuringPoint.getDesignPileLength()==null?"":measuringPoint.getDesignPileLength().toString();
                strs[9] = measuringPoint.getPzqk()==null?"":measuringPoint.getPzqk().toString();
                //4.把数组放到list中去
                list.add(strs);
            }
        }
        ExcelTool.export(filename, heads, list, response);
    }

}

