package com.yhgc.api.vo;
import com.baomidou.mybatisplus.annotation.TableField;
import com.yhgc.api.entity.MethodInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Methodinfo对象", description="检测方法分类")
public class MethodInfoVo extends MethodInfo implements Serializable {

    @ApiModelProperty(value = "方法id")
    @TableField("projectName")
    private String projectName;

}
