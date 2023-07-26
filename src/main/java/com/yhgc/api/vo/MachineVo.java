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
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
@ApiModel(value="MachineVo对象", description="仪器")
public class MachineVo implements Serializable {

    @ApiModelProperty(value = "Id")
    @TableId(value = "Id", type = IdType.AUTO)
    @JsonProperty("Id")
    private Integer Id;

    @ApiModelProperty(value = "设备类型")
    @TableField("Type")
    @JsonProperty("Type")
    private String Type;

    @ApiModelProperty(value = "设备编号")
    @TableField("DeviceId")
    @JsonProperty("DeviceId")
    private String DeviceId;

    @ApiModelProperty(value = "生产厂家")
    @TableField("Manufactor")
    @JsonProperty("Manufactor")
    private String Manufactor;

    @ApiModelProperty(value = "设备名称")
    @TableField("Name")
    @JsonProperty("Name")
    private String Name;

    @ApiModelProperty(value = "设备状态;可用 报废 停用")
    @TableField("Status")
    @JsonProperty("Status")
    private Integer Status;

    @ApiModelProperty(value = "检定状态;可用 报废 停用")
    @TableField("JdStatus")
    @JsonProperty("JdStatus")
    private Integer JdStatus;

    @ApiModelProperty(value = "检定截止日期")
    @TableField("VerifEndDate")
    @JsonProperty("VerifEndDate")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date VerifEndDate;


}
