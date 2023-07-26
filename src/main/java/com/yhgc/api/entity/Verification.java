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
 * 检定
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Verification对象", description="检定")
public class Verification implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "Id", type = IdType.AUTO)
    @JsonProperty("Id")
    private Integer Id;

    @ApiModelProperty(value = "单位编码")
    @TableField("CompId")
    @JsonProperty("CompId")
    private String CompId;

    @ApiModelProperty(value = "出厂编号")
    @TableField("MachineId")
    @JsonProperty("MachineId")
    private String MachineId;

    @ApiModelProperty(value = "计量单位")
    @TableField("VerifComp")
    @JsonProperty("VerifComp")
    private String VerifComp;

    @ApiModelProperty(value = "不确定度")
    @TableField("Uncertainty")
    @JsonProperty("Uncertainty")
    private String Uncertainty;

    @ApiModelProperty(value = "精度")
    @TableField("Accuracy")
    @JsonProperty("Accuracy")
    private String Accuracy;

    @ApiModelProperty(value = "检定周期")
    @TableField("VerificationCircle")
    @JsonProperty("VerificationCircle")
    private String VerificationCircle;

    @ApiModelProperty(value = "检定日期")
    @TableField("VerifDate")
    @JsonProperty("VerifDate")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date VerifDate;

    @ApiModelProperty(value = "检定有效期;由检定周期和检定日期自动生成")
    @TableField("VerifEndDate")
    @JsonProperty("VerifEndDate")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date VerifEndDate;

    @ApiModelProperty(value = "检定证书编号")
    @TableField("VerifNo")
    @JsonProperty("VerifNo")
    private String VerifNo;

    @ApiModelProperty(value = "检定证书（图片）")
    @TableField("VerifImage")
    @JsonProperty("VerifImage")
    private String VerifImage;

    @ApiModelProperty(value = "是否正常")
    @TableField("Valid")
    @JsonProperty("Valid")
    private String Valid;


}
