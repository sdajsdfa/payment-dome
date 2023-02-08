package com.yhgc.api.service;

import com.yhgc.api.entity.NewsInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 产品和新闻 服务类
 * </p>
 *
 * @author 易生雄
 * @since 2022-09-05
 */
public interface NewsInfoService extends IService<NewsInfo> {

    /**
     * 查询新闻近15条数据
     * @return
     */
    List<NewsInfo> queryNews();

    /**
     * 查询产品近15条数据
     * @return
     */
    List<NewsInfo> queryProduct();


}
