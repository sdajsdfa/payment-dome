package com.yhgc.api.vo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yhgc.api.entity.CompanyStruct;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 组织架构
 * </p>
 *
 * @author 易生雄
 * @since 2023-05-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="CompanyStruct对象", description="组织架构")
public class CompanyInfoVo implements Serializable {

    @ApiModelProperty(value = "单位名称")
    @TableField("CompName")
    @JsonProperty("CompName")
    private String CompName;

    @ApiModelProperty(value = "映射对象")
    @TableField("companyStructList")
    @JsonProperty("CompanyStructList")
    private List<CompanyStruct> companyStructList;


}
