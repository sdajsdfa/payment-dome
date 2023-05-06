package com.yhgc.api.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Userinfo对象", description="用户信息")
public class UserInfoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "单位id")
    @TableField("unitId")
    private Long unitId;

    @ApiModelProperty(value = "单位名称")
    @TableField("unitName")
    private String unitName;

    @ApiModelProperty(value = "部门id")
    @TableField("dptId")
    private Long dptId;

    @ApiModelProperty(value = "部门名称")
    @TableField("dptName")
    private String dptName;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "密码")
    @TableField("passWord")
    private String passWord;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "身份证号")
    @TableField("idCard")
    private String idCard;

    @ApiModelProperty(value = "手机号")
    @TableField("phoneNum")
    private String phoneNum;

    @ApiModelProperty(value = "邮箱地址")
    private String email;

    @ApiModelProperty(value = "权限")
    private String rights;

    @ApiModelProperty(value = "账户状态;0：正常 1：停用 2：删除")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @TableField("createTime")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date createTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "图片")
    private String picture;

    @ApiModelProperty(value = "角色列表")
    private List<String> roleNames;

    @ApiModelProperty(value = "所属部门")
    private List<String> organizationName;
}
