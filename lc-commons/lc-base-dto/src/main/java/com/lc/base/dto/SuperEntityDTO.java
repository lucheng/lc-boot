package com.lc.base.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "DTO父类")
public class SuperEntityDTO<T extends Serializable> {
	
	@ApiModelProperty(value = "姓名")
	protected T id;
	
}