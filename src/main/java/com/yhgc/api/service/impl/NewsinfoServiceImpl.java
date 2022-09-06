package com.yhgc.api.service.impl;

import com.yhgc.api.entity.Newsinfo;
import com.yhgc.api.mapper.NewsinfoMapper;
import com.yhgc.api.service.NewsinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
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
public class NewsinfoServiceImpl extends ServiceImpl<NewsinfoMapper, Newsinfo> implements NewsinfoService {

    @Resource
    private NewsinfoMapper newsinfoMapper;

    @Override
    public List<Newsinfo> queryNews() {
        return newsinfoMapper.queryNews();
    }

    @Override
    public List<Newsinfo> queryProduct() {
        return newsinfoMapper.queryProduct();
    }
}
