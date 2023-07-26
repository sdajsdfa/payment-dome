package com.yhgc.api.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

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
@ApiModel(value="ProjectVo对象", description="工程")
public class ProjectVo implements Serializable {

    @ApiModelProperty(value = "Id")
    @TableId(value = "Id", type = IdType.AUTO)
    @JsonProperty("Id")
    private Integer Id;

    @ApiModelProperty(value = "工程名称")
    @TableField("ProjectName")
    @JsonProperty("ProjectName")
    private String ProjectName;

    @ApiModelProperty(value = "工程详细地址")
    @TableField("ProjectAddress")
    @JsonProperty("ProjectAddress")
    private String ProjectAddress;

    @ApiModelProperty(value = "监督编号")
    @TableField("SuperviseNo")
    @JsonProperty("SuperviseNo")
    private String SuperviseNo;


    @ApiModelProperty(value = "登记时间")
    @TableField("CreationTime")
    @JsonProperty("CreationTime")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date CreationTime;

    @ApiModelProperty(value = "备案状态 1正常2提前介入3撤销")
    @TableField("FilingStatus")
    @JsonProperty("FilingStatus")
    private Integer FilingStatus;

    @ApiModelProperty(value = "平面布置图")
    @TableField("Layout")
    @JsonProperty("Layout")
    private String Layout;

    @ApiModelProperty(value = "地质资料附图")
    @TableField("Geology")
    @JsonProperty("Geology")
    private String Geology;

}
