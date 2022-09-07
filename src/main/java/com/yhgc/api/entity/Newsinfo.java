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
 * 产品和新闻
 * </p>
 *
 * @author 易生雄
 * @since 2022-09-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Newsinfo对象", description="产品和新闻")
public class Newsinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "产品和新闻d")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "简介")
    private String synopsis;

    @ApiModelProperty(value = "日期")
    private Date date;

    @ApiModelProperty(value = "数据创建日期")
    @TableField("createTime")
    private Date createTime;

    @ApiModelProperty(value = "外链接")
    private String catenate;

    @ApiModelProperty(value = "类型;0：产品    1：新闻")
    @TableField("dataType")
    private Integer dataType;

    @ApiModelProperty(value = "状态;0：正常   1：作废")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String remark;


}
