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
 * 用户
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="UserInfo2对象", description="用户")
public class UserInfo2Vo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "Id", type = IdType.AUTO)
    @JsonProperty("Id")
    private Integer Id;

    @ApiModelProperty(value = "姓名")
    @TableField("Name")
    @JsonProperty("Name")
    private String Name;

    @ApiModelProperty(value = "账号")
    @TableField("Account")
    @JsonProperty("Account")
    private String Account;

    @ApiModelProperty(value = "用户状态;1正常  0禁止")
    @TableField("Valid")
    @JsonProperty("Valid")
    private Integer Valid;

    @ApiModelProperty(value = "电子邮箱")
    @TableField("Email")
    @JsonProperty("Email")
    private String Email;

    @ApiModelProperty(value = "创建日期")
    @TableField("CreateDate")
    @JsonProperty("CreateDate")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date CreateDate;

    @ApiModelProperty(value = "单位编号")
    @TableField("CompId")
    @JsonProperty("CompId")
    private String CompId;



}