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
 * 角色
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="RoleInfo对象", description="角色")
public class RoleInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "Id", type = IdType.AUTO)
    @JsonProperty("Id")
    private Integer Id;

    @ApiModelProperty(value = "单位编码")
    @TableField("CompId")
    @JsonProperty("CompId")
    private String CompId;

    @ApiModelProperty(value = "角色名称")
    @TableField("RoleName")
    @JsonProperty("RoleName")
    private String RoleName;

    @ApiModelProperty(value = "角色描述")
    @TableField("RoleDescription")
    @JsonProperty("RoleDescription")
    private String RoleDescription;

    @ApiModelProperty(value = "角色权限;权限按照角色栏中“角色权限”进行定义，json格式，比如：	{	System:	  {	  Comp:	               {	                 look:1,	                 edit:1	               }	  Struct:1,	  Role:1,	  User:1	  }	Report:	  {	    Analysis:1,	    InterReport:1	    .......	  }	}")
    @TableField("RoleAccess")
    @JsonProperty("RoleAccess")
    private String RoleAccess;

    @ApiModelProperty(value = "是否系统角色")
    @TableField("IsAdmin")
    @JsonProperty("IsAdmin")
    private Integer IsAdmin;

    @ApiModelProperty(value = "创建时间")
    @TableField("CreateDate")
    @JsonProperty("CreateDate")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date CreateDate;

    @ApiModelProperty(value = "是否正常")
    @TableField("Valid")
    @JsonProperty("Valid")
    private Integer Valid;


}
