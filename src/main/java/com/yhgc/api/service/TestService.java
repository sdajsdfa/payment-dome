package com.yhgc.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.entity.Test;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yhgc.api.vo.TestVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 检测任务 服务类
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-23
 */
public interface TestService extends IService<Test> {

    /**
     *分页查询全部检测任务
     * @param page
     * @param query
     * @return
     */
    IPage<TestVo> pageQueryAllTest(Page<TestVo> page, @Param("query") String query,@Param("compId")String compId);


    /**
     * 查询所有低应变检测编号
     * @return
     */
    List<Test> selectTestDY();


    /**
     * 查询所有高应变检测编号
     * @return
     */
    List<Test>  selectTestGY();

    /**
     * 查询所有声波测编号
     * @return
     */
    List<Test> selectTestSC();

    /**
     * 根据ID查询检测任务
     * @return
     */
    TestVo queryTest(Integer id);

}
