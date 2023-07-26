package com.yhgc.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 检测任务
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Test对象", description="检测任务")
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "Id", type = IdType.AUTO)
    @JsonProperty("Id")
    private Integer Id;

    @ApiModelProperty(value = "单位编码")
    @TableField("CompId")
    @JsonProperty("CompId")
    private String CompId;

    @ApiModelProperty(value = "工程Id")
    @TableField("ProjectId")
    @JsonProperty("ProjectId")
    private Integer ProjectId;

    @ApiModelProperty(value = "委托类型")
    @TableField("ClientType")
    @JsonProperty("ClientType")
    private String ClientType;

    @ApiModelProperty(value = "检测方法")
    @TableField("TestMethod")
    @JsonProperty("TestMethod")
    private String TestMethod;

    @ApiModelProperty(value = "检测依据")
    @TableField("TestBase")
    @JsonProperty("TestBase")
    private String TestBase;

    @ApiModelProperty(value = "检测数量")
    @TableField("TestCount")
    @JsonProperty("TestCount")
    private Integer TestCount;

    @ApiModelProperty(value = "检测批总根数")
    @TableField("TestBatchTotal")
    @JsonProperty("TestBatchTotal")
    private Integer TestBatchTotal;

    @ApiModelProperty(value = "抽检类型")
    @TableField("SamplingType")
    @JsonProperty("SamplingType")
    private String SamplingType;

    @ApiModelProperty(value = "检测负责人")
    @TableField("Tester")
    @JsonProperty("Tester")
    private String Tester;

    @ApiModelProperty(value = "试验描述")
    @TableField("Description")
    @JsonProperty("Description")
    private String Description;

    @ApiModelProperty(value = "是否正常")
    @TableField("Valid")
    @JsonProperty("Valid")
    private String Valid;

    @ApiModelProperty(value = "检测编号")
    @TableField("SerialNo")
    @JsonProperty("SerialNo")
    private String SerialNo;

    @ApiModelProperty(value = "检测状态 5检测完成4检测中3待检测")
    @TableField("TestStatus")
    @JsonProperty("TestStatus")
    private  Integer TestStatus;

    @ApiModelProperty(value = "登记时间")
    @TableField("CreationTime")
    @JsonProperty("CreationTime")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date CreationTime;


}
