package com.yhgc.api.mapper;

import com.yhgc.api.entity.Unit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 易生雄
 * @since 2023-04-23
 */
public interface UnitMapper extends BaseMapper<Unit> {

    Boolean updateUnit(Unit unit);

}
