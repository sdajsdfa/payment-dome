package com.yhgc.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * 组织架构
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="CompanyStruct对象", description="组织架构")
public class CompanyStruct implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "Id", type = IdType.AUTO)
    @JsonProperty("Id")
    private Integer Id;

    @ApiModelProperty(value = "单位编码")
    @TableField("CompId")
    @JsonProperty("CompId")
    private String CompId;

    @ApiModelProperty(value = "部门名称")
    @TableField("DepartName")
    @JsonProperty("DepartName")
    private String DepartName;

    @ApiModelProperty(value = "是否业务部门")
    @TableField("IsBusiness")
    @JsonProperty("IsBusiness")
    private Integer IsBusiness;

    @ApiModelProperty(value = "业务范围;业务范围目前先支持高低应变、超声波，后续在增加其他内容，存储模式使用json，比如：	{	DC:{	        DYB:1,	        GYB:1	      },	SC:{	        CSB:1	}	DC表示动测,SC声测。依次类推....")
    @TableField("BusinessScope")
    @JsonProperty("BusinessScope")
    private String BusinessScope;

    @ApiModelProperty(value = "部门是否正常;1表示正常，表示不正常（不正常是被人为删除）")
    @TableField("Valid")
    @JsonProperty("Valid")
    private Integer Valid;


}
