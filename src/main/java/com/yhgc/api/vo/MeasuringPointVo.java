package com.yhgc.api.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.yhgc.api.entity.MeasuringPoint;
import com.yhgc.api.entity.MethodInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="", description="测点信息")
public class MeasuringPointVo extends MethodInfo implements Serializable {

    @ApiModelProperty(value = "方法id")
    @TableField("projectName")
    private String projectName;

    @ApiModelProperty(value = "映射对象")
    @TableField("pileList")
    private List<MeasuringPoint> pileList;
}
