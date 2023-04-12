package com.yhgc.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
 * @since 2023-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Verification对象", description="")
public class Verification implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "不确定度")
    @TableField("bqLevel")
    private String bqLevel;

    @ApiModelProperty(value = "检定证书编号")
    private String jdzsbh;

    @ApiModelProperty(value = "计量单位")
    @TableField("jdjzUnit")
    private String jdjzUnit;

    @ApiModelProperty(value = " 精度")
    private String jd;

    @ApiModelProperty(value = "检定证书")
    private String picture;

    @ApiModelProperty(value = "检定日期")
    @TableField("jdjzTime")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date jdjzTime;

    @TableField("sbId")
    private Long sbId;

    @ApiModelProperty(value = "检定有效期")
    @TableField("jdjzYxDate")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date jdjzYxDate;


}
