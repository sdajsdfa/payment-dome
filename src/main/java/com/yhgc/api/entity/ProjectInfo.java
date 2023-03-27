package com.yhgc.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 工程信息
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-28
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Projectinfo对象", description="工程信息")
public class ProjectInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "工程id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "单位id")
    @TableField("unitId")
    private Long unitId;

    @ApiModelProperty(value = "工程名称")
    @TableField("projectName")
    private String projectName;

    @ApiModelProperty(value = "工程地址")
    @TableField("projectAddr")
    private String projectAddr;

    @ApiModelProperty(value = "申报时间")
    @TableField("declareTime")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , timezone = "GMT+8")
    private Date declareTime;

    @ApiModelProperty(value = "行业id")
    @TableField("sectorId")
    private Long sectorId;

    @ApiModelProperty(value = "行业名称")
    @TableField("sectorName")
    private String sectorName;

    @ApiModelProperty(value = "检测方法id")
    @TableField("methodId")
    private Long methodId;

    @ApiModelProperty(value = "检测方法名称")
    @TableField("testMethod")
    private String testMethod;

    @ApiModelProperty(value = "流水号")
    @TableField("serialNo")
    private String serialNo;

    @ApiModelProperty(value = "报检根数")
    @TableField("testCount")
    private Integer testCount;

    @ApiModelProperty(value = "检测人id")
    @TableField("accountId")
    private Long accountId;

    @ApiModelProperty(value = "检测人姓名")
    @TableField("accountName")
    private String accountName;

    @ApiModelProperty(value = "报告模板id")
    @TableField("reportId")
    private Long reportId;

    @ApiModelProperty(value = "报告模板名称")
    @TableField("reportName")
    private String reportName;

    @ApiModelProperty(value = "工程状态;0：审核中  1：已审核   2：作废")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @TableField("createTime")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "图片")
    private String picture;

//    @ApiModelProperty(value = "图片")
//    private MultipartFile[] files;

//    @ApiModelProperty(value = "备注")
//    private Integer pageSize;
//
//    @ApiModelProperty(value = "备注")
//    private Integer pageNum;


}
