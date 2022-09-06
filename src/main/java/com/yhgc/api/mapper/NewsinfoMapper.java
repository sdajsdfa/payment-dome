package com.yhgc.api.mapper;

import com.yhgc.api.entity.Newsinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 产品和新闻 Mapper 接口
 * </p>
 *
 * @author 易生雄
 * @since 2022-09-05
 */
public interface NewsinfoMapper extends BaseMapper<Newsinfo> {

   List<Newsinfo> queryNews();

   List<Newsinfo> queryProduct();

}
