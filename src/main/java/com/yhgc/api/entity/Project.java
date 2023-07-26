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
 * 工程
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Project对象", description="工程")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Id")
    @TableId(value = "Id", type = IdType.AUTO)
    @JsonProperty("Id")
    private Integer Id;

    @ApiModelProperty(value = "单位编码")
    @TableField("CompId")
    @JsonProperty("CompId")
    private String CompId;

    @ApiModelProperty(value = "工程名称")
    @TableField("ProjectName")
    @JsonProperty("ProjectName")
    private String ProjectName;

    @ApiModelProperty(value = "工程地址（省市区）")
    @TableField("ProjectCity")
    @JsonProperty("ProjectCity")
    private String ProjectCity;

    @ApiModelProperty(value = "工程详细地址")
    @TableField("ProjectAddress")
    @JsonProperty("ProjectAddress")
    private String ProjectAddress;

    @ApiModelProperty(value = "是否提前介入")
    @TableField("Intervene")
    @JsonProperty("Intervene")
    private Integer Intervene;

    @ApiModelProperty(value = "监督编号")
    @TableField("SuperviseNo")
    @JsonProperty("SuperviseNo")
    private String SuperviseNo;

    @ApiModelProperty(value = "开工日期")
    @TableField("ConstructDate")
    @JsonProperty("ConstructDate")
    private Date ConstructDate;

    @ApiModelProperty(value = "结构形式")
    @TableField("StructType")
    @JsonProperty("StructType")
    private String StructType;

    @ApiModelProperty(value = "层数")
    @TableField("Floor")
    @JsonProperty("Floor")
    private String Floor;

    @ApiModelProperty(value = "建筑面积")
    @TableField("BuildingArea")
    @JsonProperty("BuildingArea")
    private String BuildingArea;

    @ApiModelProperty(value = "现场联系人")
    @TableField("Contact")
    @JsonProperty("Contact")
    private String Contact;

    @ApiModelProperty(value = "联系电话")
    @TableField("Phone")
    @JsonProperty("Phone")
    private String Phone;

    @ApiModelProperty(value = "现场联系人邮箱")
    @TableField("Email")
    @JsonProperty("Email")
    private String Email;

    @ApiModelProperty(value = "建设单位联系人")
    @TableField("PartyAer")
    @JsonProperty("PartyAer")
    private String PartyAer;

    @ApiModelProperty(value = "联系电话")
    @TableField("PartyAPhone")
    @JsonProperty("PartyAPhone")
    private String PartyAPhone;

    @ApiModelProperty(value = "联系人邮箱")
    @TableField("PartyAEmail")
    @JsonProperty("PartyAEmail")
    private String PartyAEmail;

    @ApiModelProperty(value = "备注")
    @TableField("Remark")
    @JsonProperty("Remark")
    private String Remark;

    @ApiModelProperty(value = "压板直径/边宽")
    @TableField("PlatenDiameter")
    @JsonProperty("PlatenDiameter")
    private String PlatenDiameter;

    @ApiModelProperty(value = "基底面积")
    @TableField("BaseArea")
    @JsonProperty("BaseArea")
    private String BaseArea;

    @ApiModelProperty(value = "基础持力层")
    @TableField("BearLayer")
    @JsonProperty("BearLayer")
    private String BearLayer;

    @ApiModelProperty(value = "地基类型")
    @TableField("FoundationType")
    @JsonProperty("FoundationType")
    private String FoundationType;

    @ApiModelProperty(value = "地基设计标高")
    @TableField("FoundationHeight")
    @JsonProperty("FoundationHeight")
    private String FoundationHeight;

    @ApiModelProperty(value = "地基承载力特征值")
    @TableField("FoundationCapacity")
    @JsonProperty("FoundationCapacity")
    private String FoundationCapacity;

    @ApiModelProperty(value = "基桩类型")
    @TableField("PileType")
    @JsonProperty("PileType")
    private String PileType;

    @ApiModelProperty(value = "桩端持力层")
    @TableField("PileBearLayer")
    @JsonProperty("PileBearLayer")
    private String PileBearLayer;

    @ApiModelProperty(value = "桩身砼强度等级")
    @TableField("Concrete")
    @JsonProperty("Concrete")
    private String Concrete;

    @ApiModelProperty(value = "工程总桩数")
    @TableField("PileCount")
    @JsonProperty("PileCount")
    private Integer PileCount;

    @ApiModelProperty(value = "设计桩长")
    @TableField("PileLength")
    @JsonProperty("PileLength")
    private String PileLength;

    @ApiModelProperty(value = "桩径")
    @TableField("PileDiameter")
    @JsonProperty("PileDiameter")
    private String PileDiameter;

    @ApiModelProperty(value = "要求最大荷载")
    @TableField("MaxBear")
    @JsonProperty("MaxBear")
    private String MaxBear;

    @ApiModelProperty(value = "单桩承载力值")
    @TableField("PileBear")
    @JsonProperty("PileBear")
    private String PileBear;

    @ApiModelProperty(value = "建设单位")
    @TableField("PartyA")
    @JsonProperty("PartyA")
    private String PartyA;

    @ApiModelProperty(value = "委托单位")
    @TableField("Client")
    @JsonProperty("Client")
    private String Client;

    @ApiModelProperty(value = "勘察单位")
    @TableField("Survey")
    @JsonProperty("Survey")
    private String Survey;

    @ApiModelProperty(value = "设计单位")
    @TableField("Design")
    @JsonProperty("Design")
    private String Design;

    @ApiModelProperty(value = "承建单位")
    @TableField("Construct")
    @JsonProperty("Construct")
    private String Construct;

    @ApiModelProperty(value = "施工单位")
    @TableField("Build")
    @JsonProperty("Build")
    private String Build;

    @ApiModelProperty(value = "监理单位")
    @TableField("Supervisor")
    @JsonProperty("Supervisor")
    private String Supervisor;

    @ApiModelProperty(value = "监督单位")
    @TableField("Supervise")
    @JsonProperty("Supervise")
    private String Supervise;

    @ApiModelProperty(value = "平面布置图")
    @TableField("Layout")
    @JsonProperty("Layout")
    private String Layout;

    @ApiModelProperty(value = "地质资料附图")
    @TableField("Geology")
    @JsonProperty("Geology")
    private String Geology;

    @ApiModelProperty(value = "是否正常")
    @TableField("Valid")
    @JsonProperty("Valid")
    private String Valid;

    @ApiModelProperty(value = "登记时间")
    @TableField("CreationTime")
    @JsonProperty("CreationTime")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date CreationTime;

    @ApiModelProperty(value = "备案状态")
    @TableField("FilingStatus")
    @JsonProperty("FilingStatus")
    private Integer FilingStatus;

    @ApiModelProperty(value = "单桩承载力类型")
    @TableField("BearType")
    @JsonProperty("BearType")
    private String BearType;

}
