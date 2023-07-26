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
import java.util.Date;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="RoleInfo对象", description="角色")
public class RoleInfoVo implements Serializable {

    @ApiModelProperty(value = "id")
    @TableId(value = "Id", type = IdType.AUTO)
    @JsonProperty("Id")
    private Integer Id;

    @ApiModelProperty(value = "角色名称")
    @TableField("RoleName")
    @JsonProperty("RoleName")
    private String RoleName;

}
