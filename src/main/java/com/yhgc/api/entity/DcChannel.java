package com.yhgc.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

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
@ApiModel(value="DcChannel对象", description="")
public class DcChannel implements Serializable {


    private static final long serialVersionUID = 1L;

    @TableId(value = "Id", type = IdType.AUTO)
    @JsonProperty("Id")
    private Integer Id;

    @ApiModelProperty(value = "对应表Dc_BasicInfo中的BasicInfoId")
    @TableField("BasicInfoId")
    @JsonProperty("BasicInfoId")
    private Integer BasicInfoId;

    @ApiModelProperty(value = "信号类型")
    @TableField("SignalType")
    @JsonProperty("SignalType")
    private Integer SignalType;

    @ApiModelProperty(value = "采样间隔（us）")
    @TableField("SampleInterval")
    @JsonProperty("SampleInterval")
    private Double SampleInterval;

    @ApiModelProperty(value = "采样增益")
    @TableField("SampleGain")
    @JsonProperty("SampleGain")
    private Integer SampleGain;

    @ApiModelProperty(value = "采样长度")
    @TableField("SampleLength")
    @JsonProperty("SampleLength")
    private Integer SampleLength;

    @ApiModelProperty(value = "传感器灵敏度")
    @TableField("SensorSensitive")
    @JsonProperty("SensorSensitive")
    private Double SensorSensitive;

    @ApiModelProperty(value = "低通滤波频率（Hz）")
    @TableField("FilterFrequency")
    @JsonProperty("FilterFrequency")
    private Integer FilterFrequency;

    @ApiModelProperty(value = "采样时间")
    @TableField("SampleTime")
    @JsonProperty("SampleTime")
    private Date SampleTime;

    @ApiModelProperty(value = "采样数据")
    @TableField("ChannelData")
    @JsonProperty("ChannelData")
    private String ChannelData;

    @ApiModelProperty(value = "上传时间")
    @TableField("CreateTime")
    @JsonProperty("CreateTime")
    private Date CreateTime;

    @ApiModelProperty(value = "上传设备厂商标识")
    @TableField("CreateName")
    @JsonProperty("CreateName")
    private String CreateName;

}
