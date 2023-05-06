package com.yhgc.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

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
 * @since 2023-04-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Unit对象", description="")
public class Unit implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "单位id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "单位名称")
    @TableField("comName")
    private String comName;

    @ApiModelProperty(value = "单位名称缩写")
    @TableField("comShortName")
    private String comShortName;

    @ApiModelProperty(value = "注册地址")
    @TableField("registCityName")
    private String registCityName;

    @ApiModelProperty(value = "注册地址编码")
    @TableField("registCityCode")
    private String registCityCode;

    @ApiModelProperty(value = "注册详细地址")
    @TableField("registAddress")
    private String registAddress;

    @ApiModelProperty(value = "当前地址")
    @TableField("comProvideName")
    private String comProvideName;

    @ApiModelProperty(value = "当前地址编码")
    @TableField("comProvideCode")
    private String comProvideCode;

    @ApiModelProperty(value = "当前详细地址")
    @TableField("comAddress")
    private String comAddress;

    @ApiModelProperty(value = "统一社会信用代码")
    @TableField("unitCode")
    private String unitCode;

    @ApiModelProperty(value = "单位邮编")
    @TableField("comZipCode")
    private String comZipCode;

    @ApiModelProperty(value = "创建日期")
    @TableField("regDate")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date regDate;

    @ApiModelProperty(value = "工商注册类型")
    @TableField("unitRegType")
    private String unitRegType;

    @ApiModelProperty(value = "工商发证机关")
    @TableField("issuingUnit")
    private String issuingUnit;

    @ApiModelProperty(value = "法人代表")
    @TableField("frDb")
    private String frDb;

    @ApiModelProperty(value = "注册资金")
    private String capital;

    @ApiModelProperty(value = "法人代表电话")
    @TableField("frdbTel")
    private String frdbTel;

    @ApiModelProperty(value = "单位联系电话")
    @TableField("lxrTel")
    private String lxrTel;

    @ApiModelProperty(value = "单位传真")
    private String fax;

    @ApiModelProperty(value = "电子邮箱")
    @TableField("etpEmail")
    private String etpEmail;

    @ApiModelProperty(value = "房屋建筑面积")
    @TableField("mjFw")
    private String mjFw;

    @ApiModelProperty(value = "企业网址")
    @TableField("etUrl")
    private String etUrl;

    @ApiModelProperty(value = "办公场所面积")
    @TableField("mjBg")
    private String mjBg;

    @ApiModelProperty(value = "实验室面积")
    @TableField("mjSys")
    private String mjSys;

    @ApiModelProperty(value = "地基基础工程检测")
    @TableField("djJc")
    private String djJc;

    @ApiModelProperty(value = "机构简介")
    private String introduction;

    @ApiModelProperty(value = "备注说名")
    @TableField("comRemark")
    private String comRemark;

    @ApiModelProperty(value = "营业执照")
    private String photo;

    @ApiModelProperty(value = "单位编码")
    private String code;


}
