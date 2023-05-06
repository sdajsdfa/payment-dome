package com.yhgc.api.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yhgc.api.entity.Menu;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * {
 * 					name: 'SysUser',
 * 					title: '用户管理',
 * 					icon: 'el-icon-s-custom',
 * 					path: '/sys/users',
 * 					component: 'sys/User',
 * 					children: []
 *
 *             icon: 'el-icon-lx-cascades',
 *             index: 'sys',
 *             path: '',
 *             name: 'sys',
 *             component: '',
 *             title: '用户管理',
 *             children: []
 */
@Data
public class MenuDto implements Serializable {

	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	private String name;

	@ApiModelProperty(value = "父菜单ID，一级菜单为0")
	private Long parentId;

	@ApiModelProperty(value = "授权(多个用逗号分隔，如：user:list,user:create)")
	private String perms;

	@ApiModelProperty(value = "子菜单")
	private List<MenuDto> childMenus;
}
