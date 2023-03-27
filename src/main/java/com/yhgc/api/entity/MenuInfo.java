package com.yhgc.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
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
 * @since 2023-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="MenuInfo对象", description="")
public class MenuInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "名称")
    @TableField("menuName")
    private String menuName;

    @ApiModelProperty(value = "父节点Id")
    @TableField("menuParentId")
    private Long menuParentId;

    @ApiModelProperty(value = "图标")
    @TableField("menuIcon")
    private String menuIcon;

    @ApiModelProperty(value = "路由")
    @TableField("menuUrl")
    private String menuUrl;

    @ApiModelProperty(value = "子菜单")
    private List<MenuInfo> childMenus;

}
