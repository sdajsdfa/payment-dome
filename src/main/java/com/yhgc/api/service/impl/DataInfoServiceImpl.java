package com.yhgc.api.service.impl;

import com.yhgc.api.entity.DataInfo;
import com.yhgc.api.mapper.DataInfoMapper;
import com.yhgc.api.service.DataInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据 服务实现类
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-28
 */
@Service
public class DataInfoServiceImpl extends ServiceImpl<DataInfoMapper, DataInfo> implements DataInfoService {

    @Resource
    private DataInfoMapper datainfoMapper;

    @Override
    public Integer countProjectinfo(Integer userId) {
        return datainfoMapper.countProjectinfo(userId);
    }

    @Override
    public Integer countFile(Integer userId) {
        return datainfoMapper.countFile(userId);
    }

    @Override
    public Integer countAnalysis(Integer userId) {
        return datainfoMapper.countAnalysis(userId);
    }

    @Override
    public Integer countMachine(Integer userId) {
        return datainfoMapper.countMachine(userId);
    }

    @Override
    public List<Map<Date,Integer>> countSevenDaysUpload(Integer userId) {
        return datainfoMapper.countSevenDaysUpload(userId);
    }

    @Override
    public List<Map<Date,Integer>> countSevenDaysAnalysis(Integer userId) {
        return datainfoMapper.countSevenDaysAnalysis(userId);
    }
}
