package com.yhgc.api.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Projectinfo对象", description="工程信息")
public class ProjectInfoDto {


    @ApiModelProperty(value = "工程ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "工程名称")
    @TableField("projectName")
    private String projectName;

    @ApiModelProperty(value = "监督编号")
    @TableField("jdCode")
    private String jdCode;

    @ApiModelProperty(value = "详细地址")
    @TableField("projectAddress")
    private String projectAddress;

    @ApiModelProperty(value = "登记时间")
    @TableField("creationTime")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date creationTime;

    @ApiModelProperty(value = "备案状态")
    @TableField("filinfStatus")
    private Integer filinfStatus;

    @ApiModelProperty(value = "工程类型")
    @TableField("projectType")
    private Integer projectType;

}
