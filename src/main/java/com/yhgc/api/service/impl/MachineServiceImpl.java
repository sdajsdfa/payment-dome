package com.yhgc.api.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhgc.api.entity.Machine;
import com.yhgc.api.mapper.MachineMapper;
import com.yhgc.api.service.MachineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yhgc.api.vo.MachineVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 仪器 服务实现类
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-24
 */
@Service
public class MachineServiceImpl extends ServiceImpl<MachineMapper, Machine> implements MachineService {

    @Resource
    private MachineMapper machineMapper;

    @Override
    public IPage<MachineVo> pageQueryAllMachine(Page<MachineVo> page, String query,String compId) {
        return machineMapper.pageQueryAllMachine(page,query,compId);
    }

    @Override
    public Boolean saveMachine(Machine machine) {
        return machineMapper.saveMachine(machine);
    }
}
