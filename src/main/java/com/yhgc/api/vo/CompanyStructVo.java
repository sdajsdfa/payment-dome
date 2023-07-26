package com.yhgc.api.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
public class CompanyStructVo implements Serializable {


    @ApiModelProperty(value = "id")
    @TableId(value = "Id", type = IdType.AUTO)
    @JsonProperty("Id")
    private Integer Id;

    @ApiModelProperty(value = "部门名称")
    @TableField("DepartName")
    @JsonProperty("DepartName")
    private String DepartName;

}
