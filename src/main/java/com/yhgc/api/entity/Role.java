package com.yhgc.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.util.ArrayList;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author 易生雄
 * @since 2023-04-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Role对象", description="")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "显色名称")
    private String name;

    @ApiModelProperty(value = "系统角色")
    @TableField("isStatic")
    private Boolean isStatic;

    @ApiModelProperty(value = "默认角色")
    @TableField("isDefault")
    private Boolean isDefault;

    @ApiModelProperty(value = "角色名称")
    @TableField("displayName")
    private String displayName;

    @ApiModelProperty(value = "创建时间")
    @TableField("creationTime")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date creationTime;

    @ApiModelProperty(value = "角色描述")
    private String description;

    @TableField(exist = false)
    private List<Long> menuIds = new ArrayList<>();


}
