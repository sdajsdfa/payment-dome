package com.yhgc.api.service.impl;

import com.yhgc.api.entity.Datainfo;
import com.yhgc.api.mapper.DatainfoMapper;
import com.yhgc.api.service.DatainfoService;
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
public class DatainfoServiceImpl extends ServiceImpl<DatainfoMapper, Datainfo> implements DatainfoService {

    @Resource
    private DatainfoMapper datainfoMapper;

    @Override
    public Integer countProjectinfo(String userId) {
        return datainfoMapper.countProjectinfo(userId);
    }

    @Override
    public Integer countFile(String userId) {
        return datainfoMapper.countFile(userId);
    }

    @Override
    public Integer countAnalysis(String userId) {
        return datainfoMapper.countAnalysis(userId);
    }

    @Override
    public Integer countMachine(String userId) {
        return datainfoMapper.countMachine(userId);
    }

    @Override
    public List<Map<Date,Integer>> countSevenDaysUpload(String userId) {
        return datainfoMapper.countSevenDaysUpload(userId);
    }

    @Override
    public List<Map<Date,Integer>> countSevenDaysAnalysis(String userId) {
        return datainfoMapper.countSevenDaysAnalysis(userId);
    }
}
