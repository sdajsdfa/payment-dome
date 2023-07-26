package com.yhgc.api.entity;

import java.math.BigDecimal;
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
 * 单位基本信息
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="CompanyInfo对象", description="单位基本信息")
public class CompanyInfo implements Serializable {

    @ApiModelProperty(value = "id")
    @TableId(value = "Id", type = IdType.AUTO)
    @JsonProperty("Id")
    private Integer Id;

    @ApiModelProperty(value = "单位编码")
    @TableField("CompId")
    @JsonProperty("CompId")
    private String CompId;

    @ApiModelProperty(value = "单位名称")
    @TableField("CompName")
    @JsonProperty("CompName")
    private String CompName;

    @ApiModelProperty(value = "单位名称缩写")
    @TableField("CompSimpleName")
    @JsonProperty("CompSimpleName")
    private String CompSimpleName;

    @ApiModelProperty(value = "注册地址(省市区);湖北省/武汉市/洪山区")
    @TableField("RegisterCity")
    @JsonProperty("RegisterCity")
    private String RegisterCity;

    @ApiModelProperty(value = "注册详细地址;XX大道62号2栋2楼1011室")
    @TableField("RegisterAddress")
    @JsonProperty("RegisterAddress")
    private String RegisterAddress;

    @ApiModelProperty(value = "当前地址(省市区);湖北省/武汉市/洪山区")
    @TableField("CurrentCity")
    @JsonProperty("CurrentCity")
    private String CurrentCity;

    @ApiModelProperty(value = "当前详细地址;XX大道62号2栋2楼1011室")
    @TableField("CurrentAddress")
    @JsonProperty("CurrentAddress")
    private String CurrentAddress;

    @ApiModelProperty(value = "统一社会信用代码")
    @TableField("UnifiedSocialCreditCode")
    @JsonProperty("UnifiedSocialCreditCode")
    private String UnifiedSocialCreditCode;

    @ApiModelProperty(value = "邮政编码")
    @TableField("PostalCode")
    @JsonProperty("PostalCode")
    private String PostalCode;

    @ApiModelProperty(value = "创建日期")
    @TableField("CreateDate")
    @JsonProperty("CreateDate")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date CreateDate;

    @ApiModelProperty(value = "工商注册类型")
    @TableField("BusinessType")
    @JsonProperty("BusinessType")
    private String BusinessType;

    @ApiModelProperty(value = "工商发证机关")
    @TableField("BusnessFrom")
    @JsonProperty("BusnessFrom")
    private String BusnessFrom;

    @ApiModelProperty(value = "法人代表")
    @TableField("LawPerson")
    @JsonProperty("LawPerson")
    private String LawPerson;

    @ApiModelProperty(value = "注册资金")
    @TableField("RegisterMoney")
    @JsonProperty("RegisterMoney")
    private BigDecimal RegisterMoney;

    @ApiModelProperty(value = "法人代表电话")
    @TableField("LawPersonPhone")
    @JsonProperty("LawPersonPhone")
    private String LawPersonPhone;

    @ApiModelProperty(value = "单位联系电话")
    @TableField("CompPhone")
    @JsonProperty("CompPhone")
    private String CompPhone;

    @ApiModelProperty(value = "单位传真")
    @TableField("CompFax")
    @JsonProperty("CompFax")
    private String CompFax;

    @ApiModelProperty(value = "电子邮箱")
    @TableField("CompEMail")
    @JsonProperty("CompEMail")
    private String CompEMail;

    @ApiModelProperty(value = "房屋建筑面积")
    @TableField("CoverArea")
    @JsonProperty("CoverArea")
    private String CoverArea;

    @ApiModelProperty(value = "企业网址")
    @TableField("CompWeb")
    @JsonProperty("CompWeb")
    private String CompWeb;

    @ApiModelProperty(value = "机构简介")
    @TableField("Introduction")
    @JsonProperty("Introduction")
    private String Introduction;

    @ApiModelProperty(value = "备注说明")
    @TableField("Remark")
    @JsonProperty("Remark")
    private String Remark;

    @ApiModelProperty(value = "营业执照")
    @TableField("License")
    @JsonProperty("License")
    private String License;


}
