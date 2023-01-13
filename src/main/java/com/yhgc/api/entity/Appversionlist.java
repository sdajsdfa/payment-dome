package com.yhgc.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * APP版本列表
 * </p>
 *
 * @author 易生雄
 * @since 2022-12-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Appversionlist对象", description="APP版本列表")
public class Appversionlist implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "版本id")
    @TableId(value = "id", type = IdType.AUTO)
    @JsonProperty("Id")
    private Long id;

    @ApiModelProperty(value = "名称id")
    @TableField("appId")
    @JsonProperty("AppId")
    private Long appId;

    @ApiModelProperty(value = "APP名称")
    @TableField("appName")
    @JsonProperty("AppName")
    private String appName;

    @ApiModelProperty(value = "APP版本")
    @TableField("appVer")
    @JsonProperty("AppVer")
    private String appVer;

    @ApiModelProperty(value = "APP上线日期")
    @TableField("appDate")
    @JsonProperty("AppDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date AppDate;

    @ApiModelProperty(value = "APP普通文件")
    @TableField("regularFile")
    @JsonProperty("RegularFile")
    private String regularFile;

    @ApiModelProperty(value = "APPjson文件")
    @TableField("jsonFile")
    @JsonProperty("JsonFile")
    private String jsonFile;

    @ApiModelProperty(value = "APP状态")
    @TableField("appFlag")
    @JsonProperty("AppFlag")
    private Integer appFlag;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "APP状态")
    @TableField("appStatus")
    @JsonProperty("AppStatus")
    private Integer appStatus;

}
