package com.zlht.pbr.algorithm.developer.remote.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author zi jian Wang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Earining {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    @ApiModelProperty(value = "佣金项", required = true)
    private String reviewId;

    @ApiModelProperty(value = "佣金项名", required = true)
    private String commitId;

    @ApiModelProperty(value = "佣金项名", required = true)
    private String commitName;

    @ApiModelProperty(value = "佣金金额", required = true)
    private Double money;

    @ApiModelProperty(value = "状态(0:未付款，1：已付款)", required = true)
    private Integer status;

    @ApiModelProperty(value = "备注", required = false)
    private String mark;

    @ApiModelProperty(value = "添加时间 iso:YYYY-MM-DDTHH:mm:ss.sssZ", required = true)
    private Date createTime;
}
