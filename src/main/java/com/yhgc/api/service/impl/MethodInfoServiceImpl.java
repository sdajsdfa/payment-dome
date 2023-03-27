package com.yhgc.api.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.entity.MethodInfo;
import com.yhgc.api.mapper.MethodInfoMapper;
import com.yhgc.api.service.MethodInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yhgc.api.vo.MethodInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 检测方法分类 服务实现类
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-28
 */
@Service
public class MethodInfoServiceImpl extends ServiceImpl<MethodInfoMapper, MethodInfo> implements MethodInfoService {

    @Resource
    private MethodInfoMapper methodInfoMapper;

    @Override
    public int saveMethodInfo(MethodInfo methodInfo) {
        return methodInfoMapper.saveMethodInfo(methodInfo);
    }

    @Override
    public IPage<MethodInfoVo> searchPage(Page<MethodInfoVo> page, String query) {
        return methodInfoMapper.searchPage(page,query);
    }

    @Override
    public MethodInfoVo getByIdMethodInfo(Long id) {
        return methodInfoMapper.getByIdMethodInfo(id);
    }

}
