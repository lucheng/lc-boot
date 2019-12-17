package com.lc.demo.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户-列表
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "用户-列表")
public class UserListDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 姓名
	 */
	@ApiModelProperty(value = "姓名")
	private String name;
}
