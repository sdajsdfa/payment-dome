package com.yhgc.api.service.impl;

import com.yhgc.api.entity.Unit;
import com.yhgc.api.entity.UnitInfo;
import com.yhgc.api.mapper.UnitMapper;
import com.yhgc.api.service.UnitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 易生雄
 * @since 2023-04-23
 */
@Service
public class UnitServiceImpl extends ServiceImpl<UnitMapper, Unit> implements UnitService {

    @Resource
    private UnitMapper unitMapper;

    @Override
    public Boolean updateUnit(Unit unit) {
        return unitMapper.updateUnit(unit);
    }
}
