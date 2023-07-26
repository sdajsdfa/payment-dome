package com.yhgc.api.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.entity.Test;
import com.yhgc.api.mapper.TestMapper;
import com.yhgc.api.service.TestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yhgc.api.vo.TestVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 检测任务 服务实现类
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-23
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

    @Resource
    private TestMapper testMapper;

    @Override
    public IPage<TestVo> pageQueryAllTest(Page<TestVo> page, String query, String compId) {
        return testMapper.pageQueryAllTest(page,query,compId);
    }

    @Override
    public List<Test> selectTestDY() {
        return testMapper.selectTestDY();
    }

    @Override
    public List<Test> selectTestGY() {
        return testMapper.selectTestGY();
    }

    @Override
    public List<Test> selectTestSC() {
        return testMapper.selectTestSC();
    }

    @Override
    public TestVo queryTest(Integer id) {
        return testMapper.queryTest(id);
    }
}
