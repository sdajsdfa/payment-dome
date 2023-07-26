package com.yhgc.api.controller;

import com.yhgc.api.entity.SbSection;
import com.yhgc.api.service.UserinfoLoginToken;
import com.yhgc.api.util.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 易生雄
 * @since 2023-06-25
 */
@RestController
@RequestMapping("/api")
public class SbSectionController extends BaseController {

    /**
     *  新增声测剖面数据
     * @param sbSection
     * @return
     */
    @ApiOperation("新增声测剖面数据 ")
    @PostMapping(value = "/ScSectionData")
    @UserinfoLoginToken
    public R scSectionData(@RequestBody SbSection sbSection) {
        Boolean sc = sbSectionService.save(sbSection);
        if (!sc) {
            return R.error("新增声测剖面数据失败");
        }
        return R.ok("新增声测剖面数据成功!");
    }

}

