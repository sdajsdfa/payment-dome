package com.yhgc.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 租户
 * </p>
 *
 * @author 易生雄
 * @since 2023-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Tenant对象", description="租户")
public class Tenant implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "租户ID")
    @TableId(value = "Id", type = IdType.AUTO)
    @JsonProperty("Id")
    private Integer Id;

    @ApiModelProperty(value = "租户名称")
    @TableField("TenantName")
    @JsonProperty("TenantName")
    private String TenantName;

    @ApiModelProperty(value = "省份编码")
    @TableField("ProvinceCode")
    @JsonProperty("ProvinceCode")
    private String ProvinceCode;

    @ApiModelProperty(value = "是否监督机构")
    @TableField("IsSupervisory")
    @JsonProperty("IsSupervisory")
    private Integer IsSupervisory;

    @ApiModelProperty(value = "版本名称")
    @TableField("Version")
    @JsonProperty("Version")
    private String Version;

    @ApiModelProperty(value = "创建时间")
    @TableField("CreateTime")
    @JsonProperty("CreateTime")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date CreateTime;

    @ApiModelProperty(value = "是否正常")
    @TableField("Valid")
    @JsonProperty("Valid")
    private Integer Valid;

    @ApiModelProperty(value = "租户标识")
    @TableField("CompId")
    @JsonProperty("CompId")
    private String CompId;


}
