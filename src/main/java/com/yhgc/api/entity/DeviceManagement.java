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
 * @since 2023-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="DeviceManagement对象", description="")
public class DeviceManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "登记时间")
    @TableField("creationTime")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date creationTime;

    @ApiModelProperty(value = "设备生产厂家")
    @TableField("factoryName")
    private String factoryName;

    @ApiModelProperty(value = "检定状态")
    @TableField("jdStatus")
    private Integer jdStatus;

//    @ApiModelProperty(value = "检定有效期")
//    @TableField("jdjzYxDate")
//    @DateTimeFormat(pattern="yyyy-MM-dd")
//    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
//    private Date jdjzYxDate;

    @ApiModelProperty(value = "设备编号")
    @TableField("sbCode")
    private String sbCode;

    @ApiModelProperty(value = "规格型号")
    @TableField("sbGuige")
    private String sbGuige;

    @ApiModelProperty(value = "设备名称")
    @TableField("sbName")
    private String sbName;

    @ApiModelProperty(value = "设备状态")
    @TableField("sbStatus")
    private Integer sbStatus;

    @ApiModelProperty(value = "设备类型")
    @TableField("sbType")
    private String sbType;

    @ApiModelProperty(value = "出厂编号")
    @TableField("sbFacCode")
    private String sbFacCode;

    @ApiModelProperty(value = "量程")
    private String lc;

    @ApiModelProperty(value = "分度值")
    @TableField("sbFdz")
    private String sbFdz;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "检定周期")
    private Integer zjzq;

    @ApiModelProperty(value = "强制检定设备")
    @TableField("connectXm")
    private Integer connectXm;

    @ApiModelProperty(value = "存放地点")
    @TableField("storeType")
    private Integer storeType;

    @ApiModelProperty(value = "起始位置")
    @TableField("startPlace")
    private String startPlace;

    @ApiModelProperty(value = "数量")
    private Integer number;

    @ApiModelProperty(value = "出厂日期")
    @TableField("ccDate")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date ccDate;

    @ApiModelProperty(value = "购置日期")
    @TableField("grTime")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date grTime;

    @ApiModelProperty(value = "购买价格")
    @TableField("sbOldValue")
    private String sbOldValue;

}
