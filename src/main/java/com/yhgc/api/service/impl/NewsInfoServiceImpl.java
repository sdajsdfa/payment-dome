package com.yhgc.api.service.impl;

import com.yhgc.api.entity.NewsInfo;
import com.yhgc.api.mapper.NewsInfoMapper;
import com.yhgc.api.service.NewsInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 产品和新闻 服务实现类
 * </p>
 *
 * @author 易生雄
 * @since 2022-09-05
 */
@Service
public class NewsInfoServiceImpl extends ServiceImpl<NewsInfoMapper, NewsInfo> implements NewsInfoService {

    @Resource
    private NewsInfoMapper newsinfoMapper;

    @Override
    public List<NewsInfo> queryNews() {
        return newsinfoMapper.queryNews();
    }

    @Override
    public List<NewsInfo> queryProduct() {
        return newsinfoMapper.queryProduct();
    }
}
