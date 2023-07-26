package com.yhgc.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 权限
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Access对象", description="权限")
public class Access implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Id")
    @TableId(value = "Id", type = IdType.AUTO)
    @JsonProperty("Id")
    private Integer Id;

    @ApiModelProperty(value = "角色ID")
    @TableField("RoleId")
    @JsonProperty("RoleId")
    private Integer RoleId;

    @ApiModelProperty(value = "系统管理")
    @TableField("SystemManage")
    @JsonProperty("SystemManage")
    private Integer SystemManage;

    @ApiModelProperty(value = "单位信息")
    @TableField("CompInfo")
    @JsonProperty("CompInfo")
    private Integer CompInfo;

    @ApiModelProperty(value = "单位信息查看")
    @TableField("CompInfoLook")
    @JsonProperty("CompInfoLook")
    private Integer CompInfoLook;

    @ApiModelProperty(value = "单位信息编辑")
    @TableField("CompInfoEdit")
    @JsonProperty("CompInfoEdit")
    private Integer CompInfoEdit;

    @ApiModelProperty(value = "组织机构")
    @TableField("Struct")
    @JsonProperty("Struct")
    private Integer Struct;

    @ApiModelProperty(value = "用户")
    @TableField("User")
    @JsonProperty("User")
    private Integer User;

    @ApiModelProperty(value = "角色")
    @TableField("Role")
    @JsonProperty("Role")
    private Integer Role;

    @ApiModelProperty(value = "报告管理")
    @TableField("ReportMamage")
    @JsonProperty("ReportMamage")
    private Integer ReportMamage;

    @ApiModelProperty(value = "数据分析")
    @TableField("Analysis")
    @JsonProperty("Analysis")
    private Integer Analysis;

    @ApiModelProperty(value = "中间报告")
    @TableField("MiddleReport")
    @JsonProperty("MiddleReport")
    private Integer MiddleReport;

    @ApiModelProperty(value = "报告编制")
    @TableField("ReportEdite")
    @JsonProperty("ReportEdite")
    private Integer ReportEdite;

    @ApiModelProperty(value = "报告提交")
    @TableField("ReportSubmit")
    @JsonProperty("ReportSubmit")
    private Integer ReportSubmit;

    @ApiModelProperty(value = "报告校核")
    @TableField("ReportCheck")
    @JsonProperty("ReportCheck")
    private Integer ReportCheck;

    @ApiModelProperty(value = "报告审核")
    @TableField("ReportReview")
    @JsonProperty("ReportReview")
    private Integer ReportReview;

    @ApiModelProperty(value = "报告批准")
    @TableField("ReportRatify")
    @JsonProperty("ReportRatify")
    private Integer ReportRatify;

    @ApiModelProperty(value = "报告发放")
    @TableField("ReportPublish")
    @JsonProperty("ReportPublish")
    private Integer ReportPublish;

    @ApiModelProperty(value = "报告上传")
    @TableField("ReportUpload")
    @JsonProperty("ReportUpload")
    private Integer ReportUpload;

    @ApiModelProperty(value = "报告作废")
    @TableField("ReportDelete")
    @JsonProperty("ReportDelete")
    private Integer ReportDelete;

    @ApiModelProperty(value = "批准作废")
    @TableField("ReportRatifyCxl")
    @JsonProperty("ReportRatifyCxl")
    private Integer ReportRatifyCxl;

    @ApiModelProperty(value = "报告查看")
    @TableField("ReportLook")
    @JsonProperty("ReportLook")
    private Integer ReportLook;

    @ApiModelProperty(value = "报告下载")
    @TableField("ReportDownload")
    @JsonProperty("ReportDownload")
    private Integer ReportDownload;

    @ApiModelProperty(value = "工程管理")
    @TableField("ProjectManage")
    @JsonProperty("ProjectManage")
    private Integer ProjectManage;

    @ApiModelProperty(value = "工程登记")
    @TableField("ProjectRegister")
    @JsonProperty("ProjectRegister")
    private Integer ProjectRegister;

    @ApiModelProperty(value = "工程查询")
    @TableField("ProjectQuery")
    @JsonProperty("ProjectQuery")
    private Integer ProjectQuery;

    @ApiModelProperty(value = "工程查询")
    @TableField("ProjectAdd")
    @JsonProperty("ProjectAdd")
    private Integer ProjectAdd;

    @ApiModelProperty(value = "工程编辑")
    @TableField("ProjectEdite")
    @JsonProperty("ProjectEdite")
    private Integer ProjectEdite;

    @ApiModelProperty(value = "工程修正")
    @TableField("ProjectAlert")
    @JsonProperty("ProjectAlert")
    private Integer ProjectAlert;

    @ApiModelProperty(value = "工程存档")
    @TableField("ProjectSave")
    @JsonProperty("ProjectSave")
    private Integer ProjectSave;

    @ApiModelProperty(value = "工程合同")
    @TableField("ProjectContract")
    @JsonProperty("ProjectContract")
    private Integer ProjectContract;

    @ApiModelProperty(value = "任务管理")
    @TableField("TaskManage")
    @JsonProperty("TaskManage")
    private Integer TaskManage;

    @ApiModelProperty(value = "检测任务")
    @TableField("TaskInfo")
    @JsonProperty("TaskInfo")
    private Integer TaskInfo;

    @ApiModelProperty(value = "任务查询")
    @TableField("TaskQuery")
    @JsonProperty("TaskQuery")
    private Integer TaskQuery;

    @ApiModelProperty(value = "任务新增")
    @TableField("TaskAdd")
    @JsonProperty("TaskAdd")
    private Integer TaskAdd;

    @ApiModelProperty(value = "任务编辑")
    @TableField("TaskEdite")
    @JsonProperty("TaskEdite")
    private Integer TaskEdite;

    @ApiModelProperty(value = "任务修正")
    @TableField("TaskAlert")
    @JsonProperty("TaskAlert")
    private Integer TaskAlert;

    @ApiModelProperty(value = "任务删除")
    @TableField("TaskDelete")
    @JsonProperty("TaskDelete")
    private Integer TaskDelete;

    @ApiModelProperty(value = "任务打印")
    @TableField("TaskPrint")
    @JsonProperty("TaskPrint")
    private Integer TaskPrint;

    @ApiModelProperty(value = "任务受理")
    @TableField("TaskAccept")
    @JsonProperty("TaskAccept")
    private Integer TaskAccept;

    @ApiModelProperty(value = "设备管理")
    @TableField("MachineManage")
    @JsonProperty("MachineManage")
    private Integer MachineManage;

    @ApiModelProperty(value = "设备信息")
    @TableField("MachineInfo")
    @JsonProperty("MachineInfo")
    private Integer MachineInfo;

    @ApiModelProperty(value = "设备查询")
    @TableField("MachineQuery")
    @JsonProperty("MachineQuery")
    private Integer MachineQuery;

    @ApiModelProperty(value = "设备新增")
    @TableField("MachineAdd")
    @JsonProperty("MachineAdd")
    private Integer MachineAdd;

    @ApiModelProperty(value = "设备编辑")
    @TableField("MachineEdite")
    @JsonProperty("MachineEdite")
    private Integer MachineEdite;

    @ApiModelProperty(value = "设备删除")
    @TableField("MachineDelete")
    @JsonProperty("MachineDelete")
    private Integer MachineDelete;

    @ApiModelProperty(value = "设备检定")
    @TableField("MachineVerif")
    @JsonProperty("MachineVerif")
    private Integer MachineVerif;

    @ApiModelProperty(value = "检测管理")
    @TableField("TestManage")
    @JsonProperty("TestManage")
    private Integer TestManage;

    @ApiModelProperty(value = "现场记录")
    @TableField("SceneInfo")
    @JsonProperty("SceneInfo")
    private Integer SceneInfo;

    @ApiModelProperty(value = "现场记录查询")
    @TableField("SceneQuery")
    @JsonProperty("SceneQuery")
    private Integer SceneQuery;

    @ApiModelProperty(value = "现场记录编辑")
    @TableField("SceneEdit")
    @JsonProperty("SceneEdit")
    private Integer SceneEdit;

    @ApiModelProperty(value = "现场记录修正")
    @TableField("SceneAlert")
    @JsonProperty("SceneAlert")
    private Integer SceneAlert;

    @ApiModelProperty(value = "检测数据")
    @TableField("TestData")
    @JsonProperty("TestData")
    private Integer TestData;

    @ApiModelProperty(value = "位置数据")
    @TableField("UnknownData")
    @JsonProperty("UnknownData")
    private Integer UnknownData;

    @ApiModelProperty(value = "检测数据下载")
    @TableField("DataDownload")
    @JsonProperty("DataDownload")
    private Integer DataDownload;

    @ApiModelProperty(value = "用户查询")
    @TableField("UserQuery")
    @JsonProperty("UserQuery")
    private Integer UserQuery;

    @ApiModelProperty(value = "用户删除")
    @TableField("UserDelete")
    @JsonProperty("UserDelete")
    private Integer UserDelete;

    @ApiModelProperty(value = "用户编辑")
    @TableField("UserEdite")
    @JsonProperty("UserEdite")
    private Integer UserEdite;

    @ApiModelProperty(value = "用户重置密码")
    @TableField("UserResetPassword")
    @JsonProperty("UserResetPassword")
    private Integer UserResetPassword;

    @ApiModelProperty(value = "用户新增")
    @TableField("UserAdd")
    @JsonProperty("UserAdd")
    private Integer UserAdd;

    @ApiModelProperty(value = "角色编辑")
    @TableField("RoleEdite")
    @JsonProperty("RoleEdite")
    private Integer RoleEdite;

    @ApiModelProperty(value = "角色添加")
    @TableField("RoleAdd")
    @JsonProperty("RoleAdd")
    private Integer RoleAdd;

    @ApiModelProperty(value = "角色删除")
    @TableField("RoleDelete")
    @JsonProperty("RoleDelete")
    private Integer RoleDelete;

    @ApiModelProperty(value = "租户配置")
    @TableField("TenantConfiguration")
    @JsonProperty("TenantConfiguration")
    private Integer TenantConfiguration;


    @ApiModelProperty(value = "租户")
    @TableField("Tenant")
    @JsonProperty("Tenant")
    private Integer Tenant;


    @ApiModelProperty(value = "租户查询")
    @TableField("TenantQuery")
    @JsonProperty("TenantQuery")
    private Integer TenantQuery;


    @ApiModelProperty(value = "租户添加")
    @TableField("TenantAdd")
    @JsonProperty("TenantAdd")
    private Integer TenantAdd;


    @ApiModelProperty(value = "租户编辑")
    @TableField("TenantEdite")
    @JsonProperty("TenantEdite")
    private Integer TenantEdite;


    @ApiModelProperty(value = "租户删除")
    @TableField("TenantDelete")
    @JsonProperty("TenantDelete")
    private Integer TenantDelete;

}
