package com.yhgc.api.service;

import com.yhgc.api.entity.DataInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据 服务类
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-28
 */
public interface DataInfoService extends IService<DataInfo> {

   /**
    * 工程数量
    * @param userId
    * @return
    */
   Integer countProjectinfo(Integer userId);

   /**
    * 文件数量
    * @param userId
    * @return
    */
   Integer countFile(Integer userId);

   /**
    * 分析文件数量
    * @param userId
    * @return
    */
   Integer countAnalysis(Integer userId);


   /**
    * 仪器数量
    * @param userId
    * @return
    */
   Integer countMachine(Integer userId);

   /**
    * 近七日上传文件数量（数组）
    * @param userId
    * @return
    */
   List<Map<Date,Integer>> countSevenDaysUpload(Integer userId);

   /**
    * 近七日分析文件数量（数组）
    * @param userId
    * @return
    */
   List<Map<Date,Integer>> countSevenDaysAnalysis(Integer userId);
}
