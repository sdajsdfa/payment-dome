package com.yhgc.api.mapper;

import com.yhgc.api.entity.Datainfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据 Mapper 接口
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-28
 */
public interface DatainfoMapper extends BaseMapper<Datainfo> {

    Integer countProjectinfo(String userId);

    Integer  countFile(String userId);

    Integer countAnalysis(String userId);

    Integer countMachine(String userId);

    List<Map<Date,Integer>> countSevenDaysUpload(String userId);

    List<Map<Date,Integer>> countSevenDaysAnalysis(String userId);
}
