package com.yhgc.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 分析结果
 * </p>
 *
 * @author 易生雄
 * @since 2022-08-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Analysisinfo对象", description="分析结果")
public class Analysisinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分析id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "单位id")
    @TableField("unitId")
    private Integer unitId;

    @ApiModelProperty(value = "工程id")
    @TableField("projectId")
    private Integer projectId;

    @ApiModelProperty(value = "工程名称")
    @TableField("projectName")
    private String projectName;

    @ApiModelProperty(value = "数据id")
    @TableField("dataId")
    private Integer dataid;

    @ApiModelProperty(value = "桩号")
    @TableField("pileNo")
    private String pileNo;

    @ApiModelProperty(value = "申报时间")
    @TableField("declareTime")
    private Date declareTime;

    @ApiModelProperty(value = "分析结果")
    @TableField("analysisResults")
    private String analysisResults;

    @ApiModelProperty(value = "数据状态;0：正常   1：作废")
    @TableField("dataStatus")
    private Integer dataStatus;

    @ApiModelProperty(value = "分析时间")
    @TableField("analyFileTime")
    private Date analyFileTime;

    @ApiModelProperty(value = "备注")
    private String remark;


}
