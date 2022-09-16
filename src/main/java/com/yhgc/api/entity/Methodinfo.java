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
public class Methodinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "检测方法d")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "行业id")
    @TableField("sectorId")
    private Long sectorId;

    @ApiModelProperty(value = "检测方法")
    @TableField("testMthod")
    private String testMthod;

    @ApiModelProperty(value = "创建时间")
    @TableField("createTime")
    private Date createTime;

    @ApiModelProperty(value = "备注")
    private String remark;


}
