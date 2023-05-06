package com.yhgc.api.service;

import com.yhgc.api.entity.Unit;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 易生雄
 * @since 2023-04-23
 */
public interface UnitService extends IService<Unit> {

   Boolean updateUnit(Unit unit);

}
