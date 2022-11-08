package com.yhgc.api.controller;
import com.yhgc.api.entity.Serverlist;
import com.yhgc.api.service.ServerlistService;
import com.yhgc.api.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 * 服务器列表 前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2022-11-07
 */
@Api(tags = "服务器列表")
@RestController
@RequestMapping("/serverlist")
public class ServerlistController {

    @Resource
    private ServerlistService serverlistService;

    /**
     *查询所有服务器列表
     * @return
     */
    @ApiOperation("查询所有服务器列表")
    @PostMapping(value = "/queryAllServerlist")
    public R queryAllServerlist() {
        List<Serverlist>  list = serverlistService.list();
        return R.ok(list);
    }
}

