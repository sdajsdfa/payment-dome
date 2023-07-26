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
@ApiModel(value="SbSection对象", description="")
public class SbSection implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Id", type = IdType.AUTO)
    @JsonProperty("Id")
    private Integer Id;

    @ApiModelProperty(value = "对应表Dc_BasicInfo中的BasiclnfoId")
    @TableField("BasicInfoId")
    @JsonProperty("BasicInfoId")
    private Integer BasicInfoId;

    @ApiModelProperty(value = "剖面名称")
    @TableField("SectionName")
    @JsonProperty("SectionName")
    private String SectionName;

    @ApiModelProperty(value = "测试方式")
    @TableField("TestMode")
    @JsonProperty("TestMode")
    private Integer TestMode;

    @ApiModelProperty(value = "声测管距离（mm）")
    @TableField("TubeDistance")
    @JsonProperty("TubeDistance")
    private Integer TubeDistance;

    @ApiModelProperty(value = "测试步距（mm）")
    @TableField("Step")
    @JsonProperty("Step")
    private Integer Step;

    @ApiModelProperty(value = "系统零声时（us）")
    @TableField("ZeroTime")
    @JsonProperty("ZeroTime")
    private Double ZeroTime;

    @ApiModelProperty(value = "采样间隔（us）")
    @TableField("SampleInterval")
    @JsonProperty("SampleInterval")
    private Double SampleInterval;

    @ApiModelProperty(value = "采样长度")
    @TableField("SampleLength")
    @JsonProperty("SampleLength")
    private Integer SampleLength;

    @ApiModelProperty(value = "测点数量")
    @TableField("NodesCount")
    @JsonProperty("NodesCount")
    private Integer NodesCount;

    @ApiModelProperty(value = "测点数据的存储格式的版本")
    @TableField("DataVersion")
    @JsonProperty("DataVersion")
    private Integer DataVersion;

    @ApiModelProperty(value = "剖面的所有测点数据")
    @TableField("SectionData")
    @JsonProperty("SectionData")
    private String SectionData;

    @ApiModelProperty(value = "上传时间")
    @TableField("CreateTime")
    @JsonProperty("CreateTime")
    private Date CreateTime;

    @ApiModelProperty(value = "上传设备厂商标识")
    @TableField("CreateName")
    @JsonProperty("CreateName")
    private String CreateName;

    @ApiModelProperty(value = "试验标识")
    @TableField("BaseInfoId")
    @JsonProperty("BaseInfoId")
    private String BaseInfoId;


}
