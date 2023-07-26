package com.yhgc.api.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yhgc.api.entity.Test;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TestVo对象", description="检测任务")
public class TestVo extends Test {

    @ApiModelProperty(value = "工程名称")
    @TableField("ProjectName")
    @JsonProperty("ProjectName")
    private String ProjectName;
}
