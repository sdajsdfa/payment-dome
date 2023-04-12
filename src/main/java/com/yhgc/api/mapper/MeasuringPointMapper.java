package com.yhgc.api.mapper;

import com.yhgc.api.entity.MeasuringPoint;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yhgc.api.vo.MeasuringPointVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 易生雄
 * @since 2023-03-31
 */
public interface MeasuringPointMapper extends BaseMapper<MeasuringPoint> {

    MeasuringPointVo getByIdMeasuringPoint(@Param("id") Long id);


    Boolean saveMeasuringPoint (MeasuringPoint measuringPoint);

    List<MeasuringPoint> listMeasuringPoint();

}
