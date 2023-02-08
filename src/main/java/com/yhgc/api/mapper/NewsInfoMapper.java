package com.yhgc.api.mapper;

import com.yhgc.api.entity.NewsInfo;
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
public interface NewsInfoMapper extends BaseMapper<NewsInfo> {

   List<NewsInfo> queryNews();

   List<NewsInfo> queryProduct();

}
