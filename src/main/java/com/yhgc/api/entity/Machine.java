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
 * 仪器
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Machine对象", description="仪器")
public class Machine implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Id")
    @TableId(value = "Id", type = IdType.AUTO)
    @JsonProperty("Id")
    private Integer Id;

    @ApiModelProperty(value = "单位编码")
    @TableField("CompId")
    @JsonProperty("CompId")
    private String CompId;

    @ApiModelProperty(value = "设备类型")
    @TableField("Type")
    @JsonProperty("Type")
    private String Type;

    @ApiModelProperty(value = "规格型号")
    @TableField("Model")
    @JsonProperty("Model")
    private String Model;

    @ApiModelProperty(value = "设备编号")
    @TableField("DeviceId")
    @JsonProperty("DeviceId")
    private String DeviceId;

    @ApiModelProperty(value = "出厂编号")
    @TableField("MachineId")
    @JsonProperty("MachineId")
    private String MachineId;

    @ApiModelProperty(value = "量程")
    @TableField("RangeNumber")
    @JsonProperty("RangeNumber")
    private String RangeNumber;

    @ApiModelProperty(value = "分度值")
    @TableField("Division")
    @JsonProperty("Division")
    private String Division;

    @ApiModelProperty(value = "备注")
    @TableField("Remark")
    @JsonProperty("Remark")
    private String Remark;

    @ApiModelProperty(value = "检定周期;以月份为单位")
    @TableField("VerificationCircle")
    @JsonProperty("VerificationCircle")
    private Integer VerificationCircle;

    @ApiModelProperty(value = "是否强制检定;0无需检定   1强制检定")
    @TableField("IsForce")
    @JsonProperty("IsForce")
    private Integer IsForce;

    @ApiModelProperty(value = "设备状态;可用 报废 停用")
    @TableField("Status")
    @JsonProperty("Status")
    private Integer Status;

    @ApiModelProperty(value = "存放地点;仓库  工地  其他")
    @TableField("Location")
    @JsonProperty("Location")
    private Integer Location;

    @ApiModelProperty(value = "起始位置")
    @TableField("Start")
    @JsonProperty("Start")
    private String Start;

    @ApiModelProperty(value = "生产厂家")
    @TableField("Manufactor")
    @JsonProperty("Manufactor")
    private String Manufactor;

    @ApiModelProperty(value = "数量")
    @TableField("Number")
    @JsonProperty("Number")
    private Integer Number;

    @ApiModelProperty(value = "购置日期")
    @TableField("AcquisitionDate")
    @JsonProperty("AcquisitionDate")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date AcquisitionDate;

    @ApiModelProperty(value = "出厂日期")
    @TableField("ProductionDate")
    @JsonProperty("ProductionDate")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date ProductionDate;

    @ApiModelProperty(value = "价格")
    @TableField("Price")
    @JsonProperty("Price")
    private BigDecimal Price;

    @ApiModelProperty(value = "是否正常")
    @TableField("Valid")
    @JsonProperty("Valid")
    private Integer Valid;

    @ApiModelProperty(value = "设备名称")
    @TableField("Name")
    @JsonProperty("Name")
    private String Name;

    @ApiModelProperty(value = "检定状态;可用 报废 停用")
    @TableField("JdStatus")
    @JsonProperty("JdStatus")
    private Integer JdStatus;


}
