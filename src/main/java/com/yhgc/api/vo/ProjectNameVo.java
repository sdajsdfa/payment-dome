package com.yhgc.api.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@ApiModel(value="Projectinfo对象", description="工程信息")
public class ProjectNameVo implements Serializable {

    @ApiModelProperty(value = "工程id")
    @TableId(value = "Id", type = IdType.AUTO)
    @JsonProperty("Id")
    private Integer Id;

    @ApiModelProperty(value = "工程名称")
    @TableField("ProjectName")
    @JsonProperty("ProjectName")
    private String ProjectName;

}
