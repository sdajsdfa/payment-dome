package com.yhgc.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 检测信息表
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PileParams对象", description="检测信息表")
public class PileParams implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("anchorSoilProperties")
    private String anchorSoilProperties;

    @TableField("anchorType")
    private String anchorType;

    private String aperture;

    @ApiModelProperty(value = "基低面积(m²)")
    @TableField("baseArea")
    private String baseArea;

    @ApiModelProperty(value = "类型")
    @TableField("czltzzType")
    private String czltzzType;

    @ApiModelProperty(value = "基础持力层")
    @TableField("dEndBearingLayer")
    private String dEndBearingLayer;

    @TableField("designAnchorLength")
    private String designAnchorLength;

    @ApiModelProperty(value = "设计桩长(m)")
    @TableField("designPileLength")
    private String designPileLength;

    @ApiModelProperty(value = "地基承载力特征值(kPa)")
    private String djczltzz;

    @ApiModelProperty(value = "地基设计标高(m)")
    private String djsjbg;

    @ApiModelProperty(value = "类型值")
    private String dzczltzz;

    @ApiModelProperty(value = "地基类型")
    @TableField("foundationType")
    private String foundationType;

    @ApiModelProperty(value = "桩径(mm)")
    @TableField("pileDiameter")
    private String pileDiameter;

    @ApiModelProperty(value = "桩端持力层")
    @TableField("pileEndBearingLayer")
    private String pileEndBearingLayer;

    @ApiModelProperty(value = "工程总桩数")
    @TableField("pileNum")
    private Integer pileNum;

    @ApiModelProperty(value = "基桩类型")
    @TableField("pileType")
    private String pileType;

    private String sjkbczlsjz;

    @ApiModelProperty(value = "压板直径/边宽(mm)")
    @TableField("ybFormat")
    private String ybFormat;

    private String ybmj;

    @ApiModelProperty(value = "要求最大荷载(kN)")
    private String yqzdhz;

    private String yqzdkbhz;

    @ApiModelProperty(value = "桩身砼强度等级")
    private String zstsjqddj;

    @TableField("projectId")
    private Long projectId;


}
