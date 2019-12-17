package com.lc.base.dto;

import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "分页请求参数")
public class InputPageDTO {

	@ApiModelProperty(value = "当前页码", required = true, example = "1", allowEmptyValue = false)
	@Min(1)
	private int pageNum = 1;

	@ApiModelProperty(value = "页大小", required = true, example = "10", allowEmptyValue = false)
	@Min(1)
	private int pageSize = 10;

}