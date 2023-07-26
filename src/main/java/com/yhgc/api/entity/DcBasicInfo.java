package com.yhgc.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 易生雄
 * @since 2023-06-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="DcBasicinfo对象", description="")
public class DcBasicInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "BasicInfoId", type = IdType.AUTO)
    @JsonProperty("BasicInfoId")
    private Integer BasicInfoId;

    @ApiModelProperty(value = "设备编号")
    @TableField("MachineId")
    @JsonProperty("MachineId")
    private String MachineId;

    @ApiModelProperty(value = "检测流水号")
    @TableField("SerialNo")
    @JsonProperty("SerialNo")
    private String SerialNo;

    @ApiModelProperty(value = "桩号")
    @TableField("PileNo")
    @JsonProperty("PileNo")
    private String PileNo;

    @ApiModelProperty(value = "检测时间")
    @TableField("TestTime")
    @JsonProperty("TestTime")
    private Date TestTime;

    @ApiModelProperty(value = "桩长(m)")
    @TableField("PileLength")
    @JsonProperty("PileLength")
    private Double PileLength;

    @ApiModelProperty(value = "桩径")
    @TableField("PileDiameter")
    @JsonProperty("PileDiameter")
    private String PileDiameter;

    @ApiModelProperty(value = "波速（m/s）")
    @TableField("PileVelocity")
    @JsonProperty("PileVelocity")
    private Double PileVelocity;

    @ApiModelProperty(value = "混凝土强度")
    @TableField("ConcreteStrength")
    @JsonProperty("ConcreteStrength")
    private String ConcreteStrength;

    @ApiModelProperty(value = "GPS定位信息是否有效")
    @TableField("GpsIsValid")
    @JsonProperty("GpsIsValid")
    private Integer GpsIsValid;

    @ApiModelProperty(value = "GPS径度（°）")
    @TableField("GpsLongitude")
    @JsonProperty("GpsLongitude")
    private Float GpsLongitude;

    @ApiModelProperty(value = "GPS纬度（°）")
    @TableField("GpsLatitude")
    @JsonProperty("GpsLatitude")
    private Float GpsLatitude;

    @ApiModelProperty(value = "检测人员上岗证编号")
    @TableField("ShangGangZheng")
    @JsonProperty("ShangGangZheng")
    private String ShangGangZheng;

    @ApiModelProperty(value = "上传时间")
    @TableField("CreateTime")
    @JsonProperty("CreateTime")
    private Date CreateTime;

    @ApiModelProperty(value = "上传设备厂商标识")
    @TableField("CreateName")
    @JsonProperty("CreateName")
    private String CreateName;

    @ApiModelProperty(value = "是否为高应变测试数据")
    @TableField("IsHighStrainTest")
    @JsonProperty("IsHighStrainTest")
    private Integer IsHighStrainTest;

    @ApiModelProperty(value = "（仅高应变适用）测点下桩长（m）")
    @TableField("LengthUnderSensor")
    @JsonProperty("LengthUnderSensor")
    private Double LengthUnderSensor;

    @ApiModelProperty(value = "锤重（kg）")
    @TableField("HammerWeight")
    @JsonProperty("HammerWeight")
    private Double HammerWeight;

    @ApiModelProperty(value = "(高应变适用)锤落距（m）")
    @TableField("HammerDropHeight")
    @JsonProperty("HammerDropHeight")
    private Double HammerDropHeight;

    @ApiModelProperty(value = "测点截面积（㎡）")
    @TableField("SectionArea")
    @JsonProperty("SectionArea")
    private Double SectionArea;

    @ApiModelProperty(value = "(仅高应变适用)桩底截面积（㎡）")
    @TableField("BottomArea")
    @JsonProperty("BottomArea")
    private Double BottomArea;

    @ApiModelProperty(value = "桩密度（kg/m³）")
    @TableField("Density")
    @JsonProperty("Density")
    private Double Density;

    @ApiModelProperty(value = "(仅高应变适用)测点截面周长（m）")
    @TableField("SectionCircum")
    @JsonProperty("SectionCircum")
    private Double SectionCircum;

    @ApiModelProperty(value = "(仅高应变适用)阻尼系数")
    @TableField("Jc")
    @JsonProperty("Jc")
    private Double Jc;

    @ApiModelProperty(value = "(仅高应变适用)贯入度（mm）")
    @TableField("DepthIn")
    @JsonProperty("DepthIn")
    private Double DepthIn;

    @ApiModelProperty(value = "(仅高应变适用)测点弹性波速（m/s）")
    @TableField("Vs")
    @JsonProperty("Vs")
    private Double Vs;

    @ApiModelProperty(value = "试验标识")
    @TableField("BaseInfoId")
    @JsonProperty("BaseInfoId")
    private String BaseInfoId;

    @ApiModelProperty(value = "数据有效性")
    @TableField("IsValid")
    @JsonProperty("IsValid")
    private Integer IsValid;

    @ApiModelProperty(value = "检测单位编码")
    @TableField("ComId")
    @JsonProperty("ComId")
    private String ComId;

    @TableField(exist = false)
    @JsonProperty("Channels")
    private List<DcChannel> Channels;

}
