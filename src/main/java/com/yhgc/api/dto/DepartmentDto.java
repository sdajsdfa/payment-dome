package com.yhgc.api.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 部门
 * </p>
 *
 * @author 易生雄
 * @since 2022-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Department对象", description="部门")
public class DepartmentDto implements Serializable {

    @ApiModelProperty(value = "部门id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "部门名称")
    @TableField("dptName")
    private String dptName;

    @ApiModelProperty(value = "是否业务部门")
    @TableField("isJobOrganization")
    private Integer isJobOrganization;

    @ApiModelProperty(value = "子部门业务范围")
    @TableField("jobScope")
    private String jobScope;

    @ApiModelProperty(value = "父类")
    @TableField("parentId")
    private Long parentId;

    @ApiModelProperty(value = "子部门")
    private List<DepartmentDto> childDpts;

    @ApiModelProperty(value = "创建时间")
    @TableField("createTime")
    private Date createTime;
}
