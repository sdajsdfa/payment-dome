package com.yhgc.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 检测方法分类
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Methodinfo对象", description="检测方法分类")
public class MethodInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "检测方法id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "行业id")
    @TableField("sectorId")
    private Long sectorId;

    @ApiModelProperty(value = "方法id")
    @TableField("methodId")
    private Integer methodId;

    @ApiModelProperty(value = "检测方法")
    @TableField("testMthod")
    private String testMthod;

    @ApiModelProperty(value = "创建时间")
    @TableField("createTime")
    private Date createTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "检测编号")
    @TableField("serialNo")
    private String serialNo;

    @ApiModelProperty(value = "状态 5(检测完成)4(检测中)3(待检测)")
    private Integer status;

    @ApiModelProperty(value = "检测负责人")
    @TableField("userInfoName")
    private String userInfoName;

    @ApiModelProperty(value = "检测负责人id")
    @TableField("userInfoId")
    private Long userInfoId;

    @ApiModelProperty(value = "抽检类型")
    @TableField("sampleRatio")
    private String sampleRatio;

    @ApiModelProperty(value = "检测依据")
    @TableField("testStandard")
    private String testStandard;

    @ApiModelProperty(value = "检测数量(根)")
    @TableField("testMum")
    private String testMum;

    @ApiModelProperty(value = "检测批总数(根)")
    @TableField("pileNum")
    private String pileNum;

}
