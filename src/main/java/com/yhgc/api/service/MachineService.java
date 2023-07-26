package com.yhgc.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.entity.Machine;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yhgc.api.vo.MachineVo;
import com.yhgc.api.vo.ProjectVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 仪器 服务类
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-24
 */
public interface MachineService extends IService<Machine> {

    /**
     *分页查询全部设备管理
     * @param page
     * @param query
     * @return
     */
    IPage<MachineVo> pageQueryAllMachine(Page<MachineVo> page, @Param("query") String query,@Param("compId")String compId);


    /**
     *  添加设备管理
     * @param machine
     * @return
     */
    Boolean saveMachine(Machine machine);

}
