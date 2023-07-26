package com.yhgc.api.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.entity.Machine;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yhgc.api.vo.MachineVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 仪器 Mapper 接口
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-24
 */
public interface MachineMapper extends BaseMapper<Machine> {

    /**
     *分页查询全部设备管理
     * @param page
     * @param query
     * @return
     */
    IPage<MachineVo> pageQueryAllMachine(@Param("page") Page<MachineVo> page, @Param("query") String query,@Param("compId")String compId);

    /**
     *  添加设备管理
     * @param machine
     * @return
     */
    Boolean saveMachine(Machine machine);

}
