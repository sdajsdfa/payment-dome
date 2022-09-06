package com.yhgc.api.service;

import com.yhgc.api.entity.Newsinfo;
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
public interface NewsinfoService extends IService<Newsinfo> {

    /**
     * 查询新闻近15条数据
     * @return
     */
    List<Newsinfo> queryNews();

    /**
     * 查询产品近15条数据
     * @return
     */
    List<Newsinfo> queryProduct();


}
