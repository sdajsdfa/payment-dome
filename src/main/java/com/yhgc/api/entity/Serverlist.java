package com.yhgc.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 服务器列表
 * </p>
 *
 * @author 易生雄
 * @since 2022-11-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Serverlist对象", description="服务器列表")
public class Serverlist implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "服务器列表id")
    @TableId(value = "id", type = IdType.AUTO)
    @JsonProperty("Id")
    private Integer id;

    @ApiModelProperty(value = "服务地址")
    @TableField("serverIp")
    @JsonProperty("ServerIp")
    private String serverIp;

    @ApiModelProperty(value = "服务端口")
    @TableField("dtuPort")
    @JsonProperty("DtuPort")
    private String dtuPort;

    @ApiModelProperty(value = "服务名称")
    @TableField("regionName")
    @JsonProperty("RegionName")
    private String regionName;

    @ApiModelProperty(value = "web地址")
    @TableField("webIp")
    @JsonProperty("WebIp")
    private String webIp;

    @ApiModelProperty(value = "web端口")
    @TableField("webPort")
    @JsonProperty("WebPort")
    private String webPort;

    @ApiModelProperty(value = "服务标志")
    @TableField("flag")
    @JsonProperty("Flag")
    private Integer flag;

    @ApiModelProperty(value = "web标志")
    @TableField("webFlag")
    @JsonProperty("WebFlag")
    private Boolean webFlag;

    @ApiModelProperty(value = "所在省市")
    @TableField("province")
    @JsonProperty("Province")
    private String province;

    @ApiModelProperty(value = "传输标志")
    @TableField("transferFlag")
    @JsonProperty("TransferFlag")
    private Integer transferFlag;

    @ApiModelProperty(value = "城市")
    @TableField("city")
    @JsonProperty("City")
    private String city;

    @ApiModelProperty(value = "代理类型")
    @TableField("proxyType")
    @JsonProperty("ProxyType")
    private String proxyType;


}
