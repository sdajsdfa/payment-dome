package com.yhgc.api.service.impl;

import com.yhgc.api.entity.MeasuringPoint;
import com.yhgc.api.mapper.MeasuringPointMapper;
import com.yhgc.api.service.MeasuringPointService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yhgc.api.vo.MeasuringPointVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 易生雄
 * @since 2023-03-31
 */
@Service
public class MeasuringPointServiceImpl extends ServiceImpl<MeasuringPointMapper, MeasuringPoint> implements MeasuringPointService {

    @Resource
    private MeasuringPointMapper measuringPointMapper;

    @Override
    public MeasuringPointVo getByIdMeasuringPoint(Long id) {
        return measuringPointMapper.getByIdMeasuringPoint(id);
    }

    @Override
    public Boolean saveMeasuringPoint(MeasuringPoint measuringPoint) {
        return measuringPointMapper.saveMeasuringPoint(measuringPoint);
    }

    @Override
    public List<MeasuringPoint> listMeasuringPoint() {
        return measuringPointMapper.listMeasuringPoint();
    }
}
