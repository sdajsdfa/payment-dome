package com.yhgc.api.service;

import com.yhgc.api.entity.DeviceManagement;
import com.yhgc.api.entity.MeasuringPoint;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yhgc.api.vo.MeasuringPointVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 易生雄
 * @since 2023-03-31
 */
public interface MeasuringPointService extends IService<MeasuringPoint> {


   MeasuringPointVo getByIdMeasuringPoint(Long id);

   Boolean saveMeasuringPoint (MeasuringPoint measuringPoint);

   List<MeasuringPoint> listMeasuringPoint();

}
