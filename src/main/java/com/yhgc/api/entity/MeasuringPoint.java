package com.yhgc.api.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lkx.util.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author 易生雄
 * @since 2023-03-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="MeasuringPoint对象", description="")
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "measuring_point")
public class MeasuringPoint implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "mpId", type = IdType.AUTO)
    @TableField("mpId")
    @Excel(title = "序号" )
    private Integer mpId;

    @ApiModelProperty(value = "桩号")
    @TableField("pileNo")
    @Excel(title = "桩号" )
    private String pileNo;

    @ApiModelProperty(value = "桩径(mm)")
    @TableField("pileDiameter")
    @Excel(title = "桩径(mm)" )
    private String pileDiameter;

    @ApiModelProperty(value = "施工桩长(m)")
    @TableField("sgPileLength")
    @Excel(title = "施工桩长(m)" )
    private String sgPileLength;

    @ApiModelProperty(value = "设计桩长")
    @TableField("designPileLength")
    @Excel(title = "设计桩长(m)" )
    private String designPileLength;

    @ApiModelProperty(value = "成桩日期")
    @TableField("pileDate")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    @Excel(title = "成桩日期" )
    private Date pileDate;

    @ApiModelProperty(value = "设计强度等级")
    @TableField("powerLevel")
    @Excel(title = "设计强度等级" )
    private String powerLevel;

    @ApiModelProperty(value = "承载力特征值(kN)")
    @TableField("pileBearing")
    @Excel(title = "承载力特征值(kN)" )
    private String pileBearing;

    @ApiModelProperty(value = "桩端持力层")
    @TableField("pileEndBearingLayer")
    @Excel(title = "桩端持力层" )
    private String pileEndBearingLayer;

    @ApiModelProperty(value = "配桩情况(下+上)")
    @Excel(title = "配桩情况(下+上)" )
    private String pzqk;

    @TableField("isValid")
    private Long isValid;


}
