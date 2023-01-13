package com.yhgc.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 易生雄
 * @since 2022-12-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Appuser对象", description="")
public class Appuser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "app用户id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户名称")
    @TableField("userName")
    private String userName;

    @ApiModelProperty(value = "密码")
    @TableField("passWord")
    private String passWord;

    @ApiModelProperty(value = "状态")
    private Integer status;


}
