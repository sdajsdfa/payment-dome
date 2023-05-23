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
 * 参建单位
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Units对象", description="参建单位")
public class Units implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "现场联系人邮箱")
    @TableField("contactEmail")
    private String contactEmail;

    @ApiModelProperty(value = "现场联系人")
    @TableField("contactName")
    private String contactName;

    @ApiModelProperty(value = "联系电话")
    @TableField("contactPhone")
    private String contactPhone;

    @TableField("jdContactEmail")
    private String jdContactEmail;

    @TableField("jdContactName")
    private String jdContactName;

    @TableField("jdContactPhone")
    private String jdContactPhone;

    @TableField("jlContactEmail")
    private String jlContactEmail;

    @TableField("jlContactName")
    private String jlContactName;

    @TableField("jlContactPhone")
    private String jlContactPhone;

    @ApiModelProperty(value = "联系人邮箱")
    @TableField("jsContactEmail")
    private String jsContactEmail;

    @ApiModelProperty(value = "建设单位联系人")
    @TableField("jsContactName")
    private String jsContactName;

    @ApiModelProperty(value = "联系电话")
    @TableField("jsContactPhone")
    private String jsContactPhone;

    @TableField("proCJComId")
    private Integer proCJComId;

    @ApiModelProperty(value = "承建单位")
    @TableField("proCJComName")
    private String proCJComName;

    @TableField("proJCComId")
    private Integer proJCComId;

    @ApiModelProperty(value = "公司名称")
    @TableField("proJCComName")
    private String proJCComName;

    @TableField("proJDComId")
    private Integer proJDComId;

    @ApiModelProperty(value = "监督单位")
    @TableField("proJDComName")
    private String proJDComName;

    @TableField("proJLComId")
    private Integer proJLComId;

    @ApiModelProperty(value = "监理单位")
    @TableField("proJLComName")
    private String proJLComName;

    @TableField("proJSComId")
    private Integer proJSComId;

    @ApiModelProperty(value = "建设单位")
    @TableField("proJSComName")
    private String proJSComName;

    @TableField("proKCComId")
    private Integer proKCComId;

    @TableField("proKCComName")
    private String proKCComName;

    @TableField("proSGComId")
    private Integer proSGComId;

    @ApiModelProperty(value = "施工单位")
    @TableField("proSGComName")
    private String proSGComName;

    @TableField("proSJComId")
    private Integer proSJComId;

    @ApiModelProperty(value = "设计单位")
    @TableField("proSJComName")
    private String proSJComName;

    @TableField("proWTComId")
    private Integer proWTComId;

    @ApiModelProperty(value = "委托单位")
    @TableField("proWTComName")
    private String proWTComName;

    @TableField("sgContactEmail")
    private String sgContactEmail;

    @TableField("sgContactName")
    private String sgContactName;

    @TableField("sgContactPhone")
    private String sgContactPhone;

    @TableField("testLeaderId")
    private Integer testLeaderId;

    @TableField("testLeaderName")
    private String testLeaderName;

    @TableField("projectId")
    private Long projectId;


}
