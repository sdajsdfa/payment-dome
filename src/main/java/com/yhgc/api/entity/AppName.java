package com.yhgc.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2022-12-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Appname对象", description="")
public class AppName implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "名称id")
    @TableId(value = "appId", type = IdType.AUTO)
    @JsonProperty("AppId")
    private Long appId;

    @ApiModelProperty(value = "app名称")
    @TableField("appName")
    @JsonProperty("AppName")
    private String appName;

    @ApiModelProperty(value = "状态")
    @TableField("appStatus")
    @JsonProperty("AppStatus")
    private Integer appStatus;


}
