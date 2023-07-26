package com.yhgc.api.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.entity.Test;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yhgc.api.vo.TestVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 检测任务 Mapper 接口
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-23
 */
public interface TestMapper extends BaseMapper<Test> {

    /**
     *分页查询全部检测任务
     * @param page
     * @param query
     * @return
     */
    IPage<TestVo> pageQueryAllTest(@Param("page") Page<TestVo> page, @Param("query") String query,@Param("compId")String compId);


    /**
     * 查询所有低应变检测编号
     * @return
     */
    List<Test> selectTestDY();


    /**
     * 查询所有声波测编号
     * @return
     */
    List<Test> selectTestSC();


    /**
     * 查询所有高应变检测编号
     * @return
     */
    List<Test>  selectTestGY();


    /**
     * 根据ID查询检测任务
     * @return
     */
    TestVo queryTest(@Param("id") Integer id);

}
