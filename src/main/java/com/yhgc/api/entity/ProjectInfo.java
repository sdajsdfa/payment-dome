package com.yhgc.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.ArrayList;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 工程信息
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-28
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Projectinfo对象", description="工程信息")
public class ProjectInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "工程ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "建筑面积（m²）")
    private String ares;

    @ApiModelProperty(value = "市编码")
    @TableField("cityCode")
    private String cityCode;

    @ApiModelProperty(value = "市名称")
    @TableField("cityName")
    private String cityName;

    @TableField("gpsLatitude")
    private String gpsLatitude;

    @TableField("gpsLongitude")
    private String gpsLongitude;

    @ApiModelProperty(value = "是否提前介入")
    @TableField("isForwordGet")
    private Boolean isForwordGet;

    @ApiModelProperty(value = "监督编号")
    @TableField("jdCode")
    private String jdCode;

    @TableField("jdUnit")
    private String jdUnit;

    @ApiModelProperty(value = "层次")
    @TableField("layersNum")
    private String layersNum;

    @ApiModelProperty(value = "详细地址")
    @TableField("projectAddress")
    private String projectAddress;

    @TableField("projectClass")
    private Integer projectClass;

    @ApiModelProperty(value = "工程名称")
    @TableField("projectName")
    private String projectName;

    @TableField("projectPart")
    private String projectPart;

    @ApiModelProperty(value = "省编码")
    @TableField("provideCode")
    private String provideCode;

    @ApiModelProperty(value = "省名称")
    @TableField("provideName")
    private String provideName;

    @ApiModelProperty(value = "备注")
    private String remark;

    @TableField("sgCode")
    private String sgCode;

    @ApiModelProperty(value = "开工日期")
    @TableField("startDate")
    private Date startDate;

    @ApiModelProperty(value = "结构形式")
    @TableField("structureType")
    private String structureType;

    @ApiModelProperty(value = "区编码")
    @TableField("townCode")
    private String townCode;

    @ApiModelProperty(value = "区名称")
    @TableField("townName")
    private String townName;

    @ApiModelProperty(value = "工程地址")
    @TableField("projectAddr")
    private String projectAddr;

    @ApiModelProperty(value = "登记时间")
    @TableField("creationTime")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date creationTime;

    @ApiModelProperty(value = "备案状态")
    @TableField("filinfStatus")
    private Integer filinfStatus;

    @ApiModelProperty(value = "图片")
    private String picture;

    @ApiModelProperty(value = "图片")
    private String photograph;

    @ApiModelProperty(value = "工程类型")
    @TableField("projectType")
    private Integer projectType;

    @TableField(exist = false)
    private PileParams pileParams;

    @TableField(exist = false)
    private Units units;

}
